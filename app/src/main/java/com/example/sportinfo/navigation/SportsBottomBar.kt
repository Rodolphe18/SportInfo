package com.example.sportinfo.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.sportinfo.ui.competitions.CompetitionsNavigationRoute
import com.example.sportinfo.ui.favorites.FavoritesNavigationRoute
import com.example.sportinfo.ui.teams.TeamsNavigationRoute
import com.example.sportinfo.ui.today.TodayNavigationRoute
import com.francotte.android.sportinfo.R
import kotlin.reflect.KClass

@Composable
fun RowScope.SportsNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = Color(0xFF6200EE),
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            indicatorColor = Color(0xFFBB86FC))
    )
}

@Composable
fun SportsNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xFFE3E8F2),
        contentColor = Color(0xFFE3E8F2),
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
fun SportsBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    SportsNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            SportsNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )

                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(stringResource(destination.titleTextId), fontWeight = FontWeight.SemiBold) }
            )
        }
    }
}

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val titleTextId: Int,
    val route: KClass<*>
) {
    COMPETITIONS(
        selectedIcon = Icon.ImageVectorIcon(SportsIcons.Competition),
        unselectedIcon = Icon.ImageVectorIcon(SportsIcons.Competition),
        titleTextId = R.string.competition,
        route = CompetitionsNavigationRoute::class
    ),
    TEAMS(
        selectedIcon = Icon.ImageVectorIcon(Icons.Filled.Flag),
        unselectedIcon = Icon.ImageVectorIcon(Icons.Filled.Flag),
        titleTextId = R.string.team,
        route = TeamsNavigationRoute::class
    ),
    TODAY(
        selectedIcon = Icon.ImageVectorIcon(Icons.Filled.Today),
        unselectedIcon = Icon.ImageVectorIcon(Icons.Filled.Today),
        titleTextId = R.string.today,
        route = TodayNavigationRoute::class
    ),
    FAVORIS(
        selectedIcon = Icon.ImageVectorIcon(Icons.Filled.HeartBroken),
        unselectedIcon = Icon.ImageVectorIcon(Icons.Filled.HeartBroken),
        titleTextId = R.string.favoris,
        route = FavoritesNavigationRoute::class
    )
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false