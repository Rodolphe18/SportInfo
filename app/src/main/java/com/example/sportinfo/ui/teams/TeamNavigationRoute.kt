package com.example.sportinfo.ui.teams

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object TeamsNavigationRoute

fun NavController.navigateToTeams(navOptions: NavOptions? = null) {
    this.navigate(TeamsNavigationRoute, navOptions)
}

fun NavGraphBuilder.teamsScreen() {
    composable<TeamsNavigationRoute> {
        TeamRoute()
    }
}