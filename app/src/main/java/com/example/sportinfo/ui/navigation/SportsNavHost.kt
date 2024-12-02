package com.example.sportinfo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sportinfo.ui.competitions.competitionScreen
import com.example.sportinfo.ui.home.homeNavigationRoute
import com.example.sportinfo.ui.home.homeScreen
import com.example.sportinfo.ui.matches.matchScreen

@Composable
fun SportsNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen()
        competitionScreen()
        matchScreen()
    }
}
