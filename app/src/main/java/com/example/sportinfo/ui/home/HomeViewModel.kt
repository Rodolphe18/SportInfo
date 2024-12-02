package com.example.sportinfo.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.domain.model.Area
import com.example.sportinfo.domain.repository.AreaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val areaRepository: AreaRepository,
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeUiState())
    val homeState = _homeState.asStateFlow()

    var isFilterSelected by mutableStateOf(false)

    init {
        viewModelScope.launch {
            areaRepository
                .getAreasList()
                .collect { areaList ->
                    areaList.let {
                        _homeState.update { it.copy(areas = emptyList()) }
                        _homeState.update { it.copy(areas = areaList) }
                    }
                }
            _homeState.update { it.copy(isLoading = false) }
        }
    }


    fun getFilterSelected(homeChipType: HomeChipType) {
        viewModelScope.launch {
            areaRepository
                .getAreasList()
                .map { list -> list.filter { it.parentAreaId == homeChipType.areaId } }
                .collect { areaList ->
                    areaList.let {
                        _homeState.update { it.copy(areas = emptyList()) }
                        _homeState.update { it.copy(areas = areaList) }
                    }
                }
            _homeState.update { it.copy(isLoading = false) }
            isFilterSelected = true
        }
    }


}

data class HomeUiState(
    val areas: List<Area>? = emptyList(),
    val isLoading: Boolean = true
)
