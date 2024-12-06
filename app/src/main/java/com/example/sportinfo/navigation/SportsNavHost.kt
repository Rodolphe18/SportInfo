package com.example.sportinfo.navigation

import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import com.example.sportinfo.domain.model.Competition
import com.example.sportinfo.ui.competitions.BaseRoute
import com.example.sportinfo.ui.competitions.competitionsScreen
import com.example.sportinfo.ui.competitions.matches.competitionMatchesScreen
import com.example.sportinfo.ui.competitions.matches.navigateToCompetitionMatches
import com.example.sportinfo.ui.favorites.favoritesScreen
import com.example.sportinfo.ui.teams.teamsScreen
import com.example.sportinfo.ui.today.todayScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
    }
}

val CompetitionType = object : NavType<Competition>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): Competition? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Competition::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): Competition {
        return Json.decodeFromString<Competition>(value)
    }

    override fun serializeAsValue(value: Competition): String {
        return Json.encodeToString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Competition) {
        bundle.putParcelable(key, value)
    }
}
