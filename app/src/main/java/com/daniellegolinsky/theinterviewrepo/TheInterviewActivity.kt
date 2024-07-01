package com.daniellegolinsky.theinterviewrepo

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.daniellegolinsky.theinterviewrepo.coolColors.ui.CoolPeopleScreen
import com.daniellegolinsky.theinterviewrepo.coolColors.ui.CoolPeopleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheInterviewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            MainScreen()
        }
    }
}