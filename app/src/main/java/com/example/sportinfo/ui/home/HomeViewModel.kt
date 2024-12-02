package com.example.sportinfo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.domain.model.Area
import com.example.sportinfo.domain.repository.AreaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val areaRepository: AreaRepository,
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeUiState())
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            areaRepository
                .getAreasList()
                .collect { areaList ->
                    areaList.let {
                        _homeState.update { it.copy(areas = areaList) }
                    }
                }
            _homeState.update { it.copy(isLoading = false) }
        }
    }
}

data class HomeUiState(
    val areas: List<Area>? = emptyList(),
    val isLoading: Boolean = true
)

enum class ContinentArea(val title: String) {
    SOUTH_AMERICA("SOUTH AMERICA"), AFRICA("AFRICA"), EUROPE("EUROPE"), ASIA("ASIA"), NORTH_AMERICA("N/C AMERICA"), OTHER("World")
}