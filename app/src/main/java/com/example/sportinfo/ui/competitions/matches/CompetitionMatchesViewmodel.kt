package com.example.sportinfo.ui.competitions.matches

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.domain.repository.MatchRepository
import com.example.sportinfo.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CompetitionMatchesViewmodel @Inject constructor(savedStateHandle: SavedStateHandle, matchesRepository: MatchRepository):ViewModel() {

    private val competitionCode = savedStateHandle.toRoute<CompetitionMatchesRoute>().competitionCode

    val matchDay = savedStateHandle.toRoute<CompetitionMatchesRoute>().matchDay

    val competitionName = savedStateHandle.toRoute<CompetitionMatchesRoute>().competitionName



    var state: StateFlow<CompetitionMatchesUiState?> =
        matchesRepository.getCompetitionMatchList(competitionCode, matchDay)
            .map { matches ->
                matches?.sortedByDescending { it.utcDate }
                    ?.let { CompetitionMatchesUiState(matches = it) }
        }.stateIn(viewModelScope, WhileUiSubscribed, CompetitionMatchesUiState())


}

data class CompetitionMatchesUiState(
    val matches : List<Match> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing : Boolean = false,
    val searchQuery: String = ""
)

