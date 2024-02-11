package com.example.funnumberfacts.ui.screen.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.funnumberfacts.data.ScreenState
import com.example.funnumberfacts.repository.NumberFactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PAGE_SIZE = 3

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val numberFactRepository: NumberFactRepository
) : ViewModel() {

    private val historyFlow = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = 1),
        pagingSourceFactory = { FactsPagingSource(numberFactRepository) }
    ).flow.cachedIn(viewModelScope)

    private val _viewState = MutableStateFlow(HomeViewState(historyFlow))
    val viewState: StateFlow<HomeViewState> = _viewState.asStateFlow()

    fun onNumberEntered(text: String) {
        _viewState.update { it.copy(textInput = text) }
    }

    fun onGetNumberFactClick() {
        val enteredNumber = _viewState.value.textInput.toIntOrNull()
        _viewState.update {
            it.copy(isValidInput = enteredNumber != null)
        }

        if (enteredNumber != null) {
            getFactAboutNumber(enteredNumber)
        }
    }

    private fun getFactAboutNumber(enteredNumber: Int) {
        viewModelScope.launch {
            runCatching {
                updateScreenState(ScreenState.Loading)
                numberFactRepository.getFactAboutNumber(enteredNumber)
            }.onSuccess {
                onSuccessRequest()
            }.onFailure { error ->
                updateScreenState(ScreenState.Error(error))
            }
        }
    }

    private fun onSuccessRequest() {
        _viewState.update {
            it.copy(
                screenState = ScreenState.Idle,
                textInput = "",
                isValidInput = true,
                needToRefreshData = true
            )
        }
    }

    fun onGetRandomNumberFactClick() {
        viewModelScope.launch {
            runCatching {
                updateScreenState(ScreenState.Loading)
                numberFactRepository.getRandomFact()
            }.onSuccess {
                onSuccessRequest()
            }.onFailure { error ->
                updateScreenState(ScreenState.Error(error))
            }
        }
    }

    fun onClearNumberInputClick() {
        _viewState.update { it.copy(textInput = "", isValidInput = true) }
    }

    private fun updateScreenState(state: ScreenState) {
        _viewState.update { it.copy(screenState = state) }
    }

    fun clearHistory() {
        viewModelScope.launch {
            numberFactRepository.clearHistory()
            _viewState.update { it.copy(needToRefreshData = true) }
        }
    }

    fun onDismissRequest() {
        _viewState.update { it.copy(screenState = ScreenState.Idle) }
    }

    fun invalidate() {
        _viewState.update { it.copy(needToRefreshData = false) }
    }
}