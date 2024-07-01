package com.daniellegolinsky.theinterviewrepo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daniellegolinsky.theinterviewrepo.coolColors.ui.CoolPeopleScreen
import com.daniellegolinsky.theinterviewrepo.coolColors.ui.CoolPeopleViewModel
import com.daniellegolinsky.theinterviewrepo.happyChips.ui.anotherTab.ui.AnotherTabScreen
import com.daniellegolinsky.theinterviewrepo.happyChips.ui.happyChips.HappyChipsScreen
import com.daniellegolinsky.theinterviewrepo.navigation.MainNavHost.COOL_PEOPLE_COOL_COLORS
import com.daniellegolinsky.theinterviewrepo.navigation.MainNavHost.HEART
import com.daniellegolinsky.theinterviewrepo.navigation.MainNavHost.HOME

@Composable
fun MainNavHost(destination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = destination) {

        composable(HOME) {
            // TODO Viewmodel, list, etc
            HappyChipsScreen(chipList = listOf())
        }
        composable(HEART) {
            AnotherTabScreen()
        }
        composable(COOL_PEOPLE_COOL_COLORS) {
            val coolViewModel = hiltViewModel<CoolPeopleViewModel>()
            coolViewModel.fetchCoolPeople()
            CoolPeopleScreen(viewModel = coolViewModel, modifier = Modifier)
        }

    }
}

object MainNavHost {
    const val HOME = "HOME"
    const val HEART = "HEART" // Some would say home IS where the heart is, but I think that was just metaphorically
    const val COOL_PEOPLE_COOL_COLORS = "COOLCOOLCOOL" // We might want that back at some point?
}
