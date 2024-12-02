package com.example.sportinfo.ui.matches

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val matchNavigationRoute = "match_route"

fun NavController.navigateToMatch(navOptions: NavOptions? = null) {
    this.navigate(matchNavigationRoute, navOptions)
}

fun NavGraphBuilder.matchScreen() {
    composable(route = matchNavigationRoute) {
        MatchRoute()
    }
}