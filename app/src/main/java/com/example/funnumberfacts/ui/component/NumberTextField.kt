package com.example.funnumberfacts.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.funnumberfacts.R
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme

@Composable
fun NumberTextField(
    text: String,
    isValidInput: Boolean,
    onNumberEntered: (String) -> Unit,
    onKeyboardAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onNumberEntered(it) },
        modifier = modifier,
        label = {
            Text(
                text = stringResource(id = R.string.enter_number)
            )
        },
        supportingText = {
            if (isValidInput.not()) {
                Text(
                    text = stringResource(id = R.string.enter_number),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isValidInput.not()) {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardAction() }
        ),
        isError = isValidInput.not(),
        singleLine = true
    )
}

@Preview
@Composable
private fun EmptyNumberTextFieldPreview() {
    FunNumberFactsTheme {
        Box(modifier = Modifier.background(Color.LightGray)) {
            NumberTextField(
                text = "",
                onNumberEntered = {},
                isValidInput = true,
                onKeyboardAction = {}
            )
        }
    }
}

@Preview
@Composable
private fun ValidNumberTextFieldPreview() {
    FunNumberFactsTheme {
        Box(modifier = Modifier.background(Color.LightGray)) {
            NumberTextField(
                text = "111",
                onNumberEntered = {},
                isValidInput = true,
                onKeyboardAction = {}
            )
        }
    }
}

@Preview
@Composable
private fun InvalidNumberTextFieldPreview() {
    FunNumberFactsTheme {
        Box(modifier = Modifier.background(Color.LightGray)) {
            NumberTextField(
                text = "kldjhgfvld",
                onNumberEntered = {},
                isValidInput = false,
                onKeyboardAction = {}
            )
        }
    }
}

