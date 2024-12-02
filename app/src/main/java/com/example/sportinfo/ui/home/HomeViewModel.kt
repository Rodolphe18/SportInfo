package com.example.sportinfo.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.domain.model.Area
import com.example.sportinfo.domain.repository.AreaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val areaRepository: AreaRepository,
) : ViewModel() {

    var homeState by mutableStateOf(HomeUiState())

    init {
        viewModelScope.launch {
            areaRepository
                .getAreasList()
                .collect { areaList ->
                    areaList.let { homeState = homeState.copy(areas = areaList) }
                }
        }
    }
}

data class HomeUiState(
    val areas: List<Area> = emptyList(),
    val isLoading: Boolean = false
)