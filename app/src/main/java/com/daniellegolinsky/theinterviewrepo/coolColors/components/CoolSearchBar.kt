package com.daniellegolinsky.theinterviewrepo.coolColors.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CoolSearchBar(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    Row(modifier = modifier) {
        // There's an experimental SearchBar(...) I'd like to look at...
        TextField(
            value = text,
            maxLines = 1,
            onValueChange = onValueChange
        )
    }
}