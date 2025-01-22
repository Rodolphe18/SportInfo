package com.francotte.android.sportinfo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.francotte.android.sportinfo.ui.competitions.BaseRoute
import com.francotte.android.sportinfo.ui.competitions.competitionsScreen
import com.francotte.android.sportinfo.ui.competitions.matches.competitionMatchesScreen
import com.francotte.android.sportinfo.ui.competitions.matches.navigateToCompetitionMatches
import com.francotte.android.sportinfo.ui.search.searchScreen
import com.francotte.android.sportinfo.ui.teams.teamsScreen
import com.francotte.android.sportinfo.ui.today.todayScreen

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
        searchScreen(navController::popBackStack, {}, {})
    }
}


