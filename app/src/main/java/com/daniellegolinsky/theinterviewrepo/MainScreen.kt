package com.daniellegolinsky.theinterviewrepo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.daniellegolinsky.theinterviewrepo.navigation.MainNavHost
import com.daniellegolinsky.theinterviewrepo.navigation.MainNavHost.HEART
import com.daniellegolinsky.theinterviewrepo.navigation.MainNavHost.HOME

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    // TODO We'd actually move this into a viewmodel and control it via that, but this is fine for now
    var destination = remember { mutableStateOf(HOME) }
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        content = { contentPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .consumeWindowInsets(contentPadding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    )
            ) {
                MainNavHost(destination = destination.value)
            }
        },
        bottomBar =  {
            // TODO NavBarComponent? Would Require VM for data instead of remembered destination
            NavigationBar(
                modifier = modifier,
                containerColor = Color.Magenta,
                contentColor = Color.White,
            ) {
                NavigationBarItem(
                    selected = destination.value == HOME,
                    onClick = { destination.value = HOME },
                    icon = { Icon(Icons.Filled.Home, "Home") },
                )
                NavigationBarItem(
                    selected = destination.value == HEART,
                    onClick = { destination.value = HEART },
                    icon = { Icon(Icons.Filled.Favorite, "Heart") },
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
