package com.francotte.android.sportinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.francotte.android.sportinfo.ui.SportsApp
import com.francotte.android.sportinfo.ui.competitions.CompetitionListViewModel
import com.francotte.android.sportinfo.ui.theme.SportInfoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val competitionsViewModel: CompetitionListViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            competitionsViewModel.state.isLoading
        }
        enableEdgeToEdge()
        setContent {
            SportInfoTheme {

                    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

                    SportsApp(windowSizeClass = calculateWindowSizeClass(this), showSettingsDialog = showSettingsDialog, onSettingsDismissed = { showSettingsDialog = false }, onTopAppBarActionClick = { showSettingsDialog = true })
            }
        }
    }
}
