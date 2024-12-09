package com.example.sportinfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.zIndex
import com.example.sportinfo.navigation.SportsBottomBar
import com.example.sportinfo.navigation.SportsIcons
import com.example.sportinfo.navigation.SportsNavHost
import com.example.sportinfo.navigation.SportsTopAppBar
import com.example.sportinfo.navigation.SportsAppState
import com.example.sportinfo.navigation.rememberSportsAppState
import com.francotte.android.sportinfo.R

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SportsApp(
    windowSizeClass: WindowSizeClass,
    appState: SportsAppState = rememberSportsAppState(windowSizeClass = windowSizeClass)
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color(0xFFBDDBFE),Color(0xFFE3E8F2)))))
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
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
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent
                        ),
                        onActionClick = { appState.setShowSettingsDialog(true) }
                    )
                }
            },
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    SportsBottomBar(
                        modifier = Modifier.drawBehind {  Color(0xFFBDDBFE) },
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
}
