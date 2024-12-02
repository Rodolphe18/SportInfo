package com.example.sportinfo.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportinfo.ui.navigation.SportsBackground
import com.example.sportinfo.ui.navigation.SportsBottomBar
import com.example.sportinfo.ui.navigation.SportsGradientBackground
import com.example.sportinfo.ui.navigation.SportsIcons
import com.example.sportinfo.ui.navigation.SportsNavHost
import com.example.sportinfo.ui.navigation.SportsTopAppBar
import com.example.sportinfo.ui.navigation.TopLevelDestination
import com.example.sportinfo.ui.state.SportsAppState
import com.example.sportinfo.ui.state.rememberSportsAppState
import com.francotte.android.R

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class, ExperimentalLifecycleComposeApi::class
)
@Composable
fun SportsApp(
    windowSizeClass: WindowSizeClass,
    appState: SportsAppState = rememberSportsAppState(
        windowSizeClass = windowSizeClass
    ),
) {
    val background: @Composable (@Composable () -> Unit) -> Unit =
        when (appState.currentTopLevelDestination) {
            TopLevelDestination.HOME -> {
                    content ->
                SportsGradientBackground(content = content)
            }
            else -> { content -> SportsBackground(content = content) }
        }

    background {

        val snackbarHostState = remember { SnackbarHostState() }

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
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier.testTag("SportsBottomBar")
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
                    onBackClick = appState::onBackClick,

                    modifier = Modifier
                        .padding(padding)
                        .consumedWindowInsets(padding)
                )
            }
        }
    }
}