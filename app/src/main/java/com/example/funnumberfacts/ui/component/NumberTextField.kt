package com.example.funnumberfacts.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.funnumberfacts.R
import com.example.funnumberfacts.ui.theme.BurntSienna
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme
import com.example.funnumberfacts.ui.theme.LightGray
import com.example.funnumberfacts.ui.theme.OldLavender
import com.example.funnumberfacts.ui.theme.Thunder

@Composable
fun NumberTextField(
    text: String,
    isValidInput: Boolean,
    onNumberEntered: (String) -> Unit,
    onClearNumberInputClick: () -> Unit,
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
            if (text.isNotEmpty() || isValidInput.not()) {
                IconButton(onClick = { onClearNumberInputClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardAction() }
        ),
        isError = isValidInput.not(),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = Thunder,
            unfocusedLeadingIconColor = OldLavender,
            unfocusedBorderColor = Thunder,
            focusedLeadingIconColor = Thunder,
            focusedTextColor = Thunder,
            errorTextColor = Thunder,
            unfocusedTextColor = Thunder,
            errorTrailingIconColor = BurntSienna,
            focusedTrailingIconColor = Thunder,
            unfocusedTrailingIconColor = Thunder
        )
    )
}

@Preview
@Composable
private fun EmptyNumberTextFieldPreview() {
    FunNumberFactsTheme {
        Box(modifier = Modifier.background(LightGray)) {
            NumberTextField(
                text = "",
                onNumberEntered = {},
                isValidInput = true,
                onClearNumberInputClick = {},
                onKeyboardAction = {}
            )
        }
    }
}

@Preview
@Composable
private fun ValidNumberTextFieldPreview() {
    FunNumberFactsTheme {
        Box(modifier = Modifier.background(LightGray)) {
            NumberTextField(
                text = "111",
                onNumberEntered = {},
                isValidInput = true,
                onClearNumberInputClick = {},
                onKeyboardAction = {}
            )
        }
    }
}

@Preview
@Composable
private fun InvalidNumberTextFieldPreview() {
    FunNumberFactsTheme {
        Box(modifier = Modifier.background(LightGray)) {
            NumberTextField(
                text = "kldjhgfvld",
                onNumberEntered = {},
                isValidInput = false,
                onClearNumberInputClick = {},
                onKeyboardAction = {}
            )
        }
    }
}

