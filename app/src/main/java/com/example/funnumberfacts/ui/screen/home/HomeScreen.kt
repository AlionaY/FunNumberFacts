package com.example.funnumberfacts.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.funnumberfacts.R
import com.example.funnumberfacts.ui.component.NumberFactButton
import com.example.funnumberfacts.ui.component.NumberTextField

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsState()
    val (text, isValidInput) = viewState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            NumberTextField(
                text = text,
                onNumberEntered = viewModel::onNumberEntered,
                isValidInput = isValidInput,
                modifier = Modifier.weight(1f)
            )

            NumberFactButton(
                onClick = viewModel::onGetNumberFactClick,
                modifier = Modifier.padding(start = 10.dp, top = 8.dp),
                text = stringResource(id = R.string.get_fact)
            )
        }

        NumberFactButton(
            onClick = viewModel::onGetRandomFactClick,
            text = stringResource(id = R.string.get_fact_about_random_number),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )
    }
}