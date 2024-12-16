package com.example.sportinfo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sportinfo.ui.competitions.BaseRoute
import com.example.sportinfo.ui.competitions.competitionsScreen
import com.example.sportinfo.ui.competitions.matches.competitionMatchesScreen
import com.example.sportinfo.ui.competitions.matches.navigateToCompetitionMatches
import com.example.sportinfo.ui.favorites.favoritesScreen
import com.example.sportinfo.ui.search.searchScreen
import com.example.sportinfo.ui.teams.teamsScreen
import com.example.sportinfo.ui.today.todayScreen

@Composable
fun SportsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Any = BaseRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        competitionsScreen(onCompetitionClick = navController::navigateToCompetitionMatches) {
            competitionMatchesScreen(onBackClick = navController::popBackStack)
        }
        todayScreen()
        teamsScreen()
        favoritesScreen()
        searchScreen(navController::popBackStack, {}, {})
    }
}


