package com.example.funnumberfacts.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.funnumberfacts.R
import com.example.funnumberfacts.data.ScreenState
import com.example.funnumberfacts.ui.theme.Melanzane
import com.example.funnumberfacts.ui.theme.OldLavender
import com.example.funnumberfacts.ui.theme.WhiteLilac

@Composable
fun HandleProgressBar(screenState: ScreenState) {
    var showProgressBar by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = screenState) {
        showProgressBar = screenState == ScreenState.Loading
    }

    if (showProgressBar) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(44.dp),
                color = OldLavender
            )
        }
    }
}


@Composable
fun HandleError(
    screenState: ScreenState,
    onAcceptErrorDialog: () -> Unit
) {
    if (screenState is ScreenState.Error) {
        val exception by remember { mutableStateOf(screenState.error) }

        Dialog(
            onDismissRequest = onAcceptErrorDialog,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            val shape = RoundedCornerShape(20.dp)
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = shape
            ) {
                Column(
                    modifier = Modifier
                        .background(WhiteLilac, shape)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.oops),
                        modifier = Modifier.padding(top = 24.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 30.sp,
                        color = Melanzane,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = exception.message.orEmpty(),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp,
                        color = Melanzane,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = onAcceptErrorDialog,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.close),
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}