package com.example.sportinfo.ui.competitions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
object CompetitionsNavigationRoute // route to competitions screen

@Serializable
object BaseRoute // route to base navigation graph


fun NavController.navigateToCompetitions(navOptions: NavOptions? = null) {
    this.navigate(CompetitionsNavigationRoute, navOptions)
}

fun NavGraphBuilder.competitionsScreen(onCompetitionClick: (String, String, Int) -> Unit, competitionMatchesDestination: NavGraphBuilder.() -> Unit) {
    navigation<BaseRoute>(startDestination = CompetitionsNavigationRoute) {
        composable<CompetitionsNavigationRoute>() {
            CompetitionsRoute(onCompetitionClick = onCompetitionClick)
        }
        competitionMatchesDestination()
    }
}