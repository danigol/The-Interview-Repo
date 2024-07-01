package com.daniellegolinsky.theinterviewrepo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.daniellegolinsky.theinterviewrepo.navigation.MainNavHost

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // TODO We'd actually move this into a viewmodel and control it via that, but this is fine for now
        var destination = remember { mutableStateOf(MainNavHost.COOL_PEOPLE_COOL_COLORS) }
        MainNavHost(destination = destination.value)
        // TODO Add simple navigation bar here
        // TODO NavBarComponent?
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
