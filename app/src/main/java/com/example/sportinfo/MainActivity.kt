package com.example.sportinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sportinfo.ui.SportsApp
import com.example.sportinfo.ui.competitions.CompetitionListViewModel
import com.example.sportinfo.ui.competitions.CompetitionsListState
import com.example.sportinfo.ui.theme.LocalGradientColors
import com.example.sportinfo.ui.theme.SportInfoGradientBackground
import com.example.sportinfo.ui.theme.SportInfoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

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
