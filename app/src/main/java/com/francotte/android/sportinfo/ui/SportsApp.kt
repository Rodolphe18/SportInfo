package com.francotte.android.sportinfo.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import com.francotte.android.sportinfo.R
import com.francotte.android.sportinfo.navigation.SportsAppState
import com.francotte.android.sportinfo.navigation.SportsBottomBar
import com.francotte.android.sportinfo.navigation.SportsIcons
import com.francotte.android.sportinfo.navigation.SportsNavHost
import com.francotte.android.sportinfo.navigation.SportsTopAppBar
import com.francotte.android.sportinfo.navigation.rememberSportsAppState
import com.francotte.android.sportinfo.ui.settings.SettingsDialog

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun SportsApp(
    windowSizeClass: WindowSizeClass,
    onTopAppBarActionClick: () -> Unit,
    onSettingsDismissed: () -> Unit,
    showSettingsDialog: Boolean,
    appState: SportsAppState = rememberSportsAppState(windowSizeClass = windowSizeClass)
) {

    val snackbarHostState = remember { SnackbarHostState() }

    if (showSettingsDialog) {
        SettingsDialog(
            onDismiss = { onSettingsDismissed() },
        )
    }
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            // Show the top app bar on top level destinations.
            val destination = appState.currentTopLevelDestination

            if (destination != null) {
                SportsTopAppBar(
                    modifier = Modifier.zIndex(-1F),
                    titleRes = destination.titleTextId,
                    actionIcon = SportsIcons.Settings,
                    actionIconContentDescription = stringResource(
                        id = R.string.settings
                    ),
                    onActionClick = onTopAppBarActionClick,
                    onNavigationClick = { appState.navigateToSearch() }
                )
            }
        },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                SportsBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {

            SportsNavHost(
                navController = appState.navController,
                modifier = Modifier
                    .padding(padding)
                    .consumeWindowInsets(padding)
            )
        }
    }

}
