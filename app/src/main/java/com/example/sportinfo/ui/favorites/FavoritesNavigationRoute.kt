package com.example.sportinfo.ui.favorites

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object FavoritesNavigationRoute

fun NavController.navigateToFavorites(navOptions: NavOptions? = null) {
    this.navigate(FavoritesNavigationRoute, navOptions)
}

fun NavGraphBuilder.favoritesScreen() {
    composable<FavoritesNavigationRoute> {
        FavoritesRoute()
    }
}