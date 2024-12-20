package com.example.sportinfo.ui.competitions.matches

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CompetitionMatchesRoute(val competitionCode: String, val competitionName:String, val matchDay:Int)

fun NavController.navigateToCompetitionMatches(competitionId: String, competitionName: String, matchDay:Int, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = CompetitionMatchesRoute(competitionId, competitionName, matchDay)) {
        navOptions()
    }
}

fun NavGraphBuilder.competitionMatchesScreen(onBackClick:() -> Unit) {
    composable<CompetitionMatchesRoute> {
        PagerMatchDay()
    }
}