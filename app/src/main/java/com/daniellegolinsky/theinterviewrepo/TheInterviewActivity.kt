package com.daniellegolinsky.theinterviewrepo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.daniellegolinsky.theinterviewrepo.ui.coolfolks.CoolPeopleScreen
import com.daniellegolinsky.theinterviewrepo.ui.coolfolks.CoolPeopleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheInterviewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                val coolViewModel = hiltViewModel<CoolPeopleViewModel>()
                coolViewModel.fetchCoolPeople()
                CoolPeopleScreen(viewModel = coolViewModel, modifier = Modifier)
            }
        }
    }
}