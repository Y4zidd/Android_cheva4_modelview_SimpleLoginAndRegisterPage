@file:JvmName("TextFieldKt")

package com.example.android_cheva4_viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    label: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon:  @Composable (() -> Unit)
) {
    Column(
        modifier = modifier//.padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            //label = { Text(label) },
            value = value,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon,
            placeholder = {
                Text(text = hint)
            } ,
            singleLine = true
        )
    }
}

