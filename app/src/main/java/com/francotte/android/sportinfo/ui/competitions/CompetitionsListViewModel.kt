package com.francotte.android.sportinfo.ui.competitions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francotte.android.sportinfo.domain.model.Competition
import com.francotte.android.sportinfo.domain.repository.CompetitionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionListViewModel @Inject constructor(
    private val repositoryInterface: CompetitionRepository
) : ViewModel() {

    var state by mutableStateOf(CompetitionsListState())

    init {
        viewModelScope.launch {
            repositoryInterface
                .getCompetitionsList()
                .collect { competitionList ->
                competitionList
                    .filter { it.type == "LEAGUE" }
                    .sortedBy { it.area?.parentArea == "EUROPE" }
                    .reversed()
                    .let { list ->
                    state = state.copy(competitions = list)
                }
            }
            state = state.copy(isLoading = false)
        }
    }
}

data class CompetitionsListState(
    val competitions : List<Competition> = emptyList(),
    val isLoading: Boolean = true,
    val isRefreshing : Boolean = false,
    val searchQuery: String = ""
)
