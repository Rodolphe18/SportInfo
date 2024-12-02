package com.example.sportinfo.ui.competitions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.domain.model.Competition
import com.example.sportinfo.domain.repository.CompetitionRepository
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
                competitionList.let { list ->
                    state = state.copy(competitions = list)
                }
            }
        }
    }
}

data class CompetitionsListState(
    val competitions : List<Competition> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing : Boolean = false,
    val searchQuery: String = ""
)
