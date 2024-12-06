package com.example.sportinfo.ui.today

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object TodayNavigationRoute

fun NavController.navigateToToday(navOptions: NavOptions? = null) {
    this.navigate(TodayNavigationRoute, navOptions)
}

fun NavGraphBuilder.todayScreen() {
    composable<TodayNavigationRoute> {
        TodayRoute()
    }
}