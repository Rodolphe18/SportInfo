package com.example.sportinfo.ui.competitions.matches

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CompetitionMatchesRoute(val competitionCode: String)

fun NavController.navigateToCompetitionMatches(competitionId: String, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = CompetitionMatchesRoute(competitionId)) {
        navOptions()
    }
}

fun NavGraphBuilder.competitionMatchesScreen(onBackClick:() -> Unit) {
    composable<CompetitionMatchesRoute> {
        CompetitionMatchesScreen(onBackClick = onBackClick)
    }
}