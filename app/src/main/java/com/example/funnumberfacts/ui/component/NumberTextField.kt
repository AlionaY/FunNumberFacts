package com.example.funnumberfacts.ui.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.funnumberfacts.R

@Composable
fun NumberTextField(
    text: String,
    onNumberEntered: (String) -> Unit,
    isValidInput: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.contains(Regex("[%@*().-]")).not()) {
                onNumberEntered(it)
            }
        },
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
            onDone = {
//                    todo: make request
            },
            onNext = {
//                    todo: make request
            },
            onSearch = {
//                    todo: make request
            }
        ),
        isError = isValidInput.not(),
        singleLine = true
    )
}