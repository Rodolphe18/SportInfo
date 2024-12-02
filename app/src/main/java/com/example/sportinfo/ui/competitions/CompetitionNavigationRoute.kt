package com.example.sportinfo.ui.competitions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val competitionNavigationRoute = "competition_route"

fun NavController.navigateToCompetition(navOptions: NavOptions? = null) {
    this.navigate(competitionNavigationRoute, navOptions)
}

fun NavGraphBuilder.competitionScreen() {
    composable(route = competitionNavigationRoute) {
        CompetitionRoute()
    }
}