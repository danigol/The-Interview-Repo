package com.daniellegolinsky.theinterviewrepo.uiComponents

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainBottomNavBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    items: String,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = backgroundColor,
        contentColor = contentColor,
    ) {

    }
}
