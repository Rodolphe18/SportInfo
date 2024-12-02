package com.example.sportinfo.ui.state

import com.example.sportinfo.domain.model.Area

sealed interface OnboardingUiState {

    object Loading : OnboardingUiState
    object LoadFailed : OnboardingUiState
    object NotShown : OnboardingUiState
    data class Succeed(val areas: List<Area>) : OnboardingUiState
}