package com.example.sportinfo.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.DarkThemeConfig
import com.example.sportinfo.ThemeBrand
import com.example.sportinfo.data.datastore.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> =
        userDataRepository.userData
            .map { userData ->
                SettingsUiState.Success(
                    settings = UserEditableSettings(
                        brand = userData.themeBrand,
                        darkThemeConfig = userData.darkThemeConfig,
                    ),
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(5.seconds.inWholeMilliseconds),
                initialValue = SettingsUiState.Loading,
            )

    fun updateThemeBrand(themeBrand: com.example.sportinfo.data.datastore.ThemeBrand) {
        viewModelScope.launch {
            userDataRepository.setThemeBrand(themeBrand)
        }
    }

    fun updateDarkThemeConfig(darkThemeConfig: com.example.sportinfo.data.datastore.DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.setDarkThemeConfig(darkThemeConfig)
        }
    }

}

/**
 * Represents the settings which the user can edit within the app.
 */
data class UserEditableSettings(
    val brand: com.example.sportinfo.data.datastore.ThemeBrand,
    val darkThemeConfig: com.example.sportinfo.data.datastore.DarkThemeConfig,
)

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val settings: UserEditableSettings) : SettingsUiState
}