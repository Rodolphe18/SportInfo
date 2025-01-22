package com.francotte.android.sportinfo.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francotte.android.sportinfo.domain.model.Team
import com.francotte.android.sportinfo.domain.repository.TeamRepository
import com.francotte.android.sportinfo.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TeamsByChampionshipViewModel @Inject constructor(teamRepository: TeamRepository): ViewModel() {


    private val ligue1Teams =
        teamRepository.getLigue1Teams().stateIn(viewModelScope, WhileUiSubscribed, null)
    private val bundesligaTeams =
        teamRepository.getBundesligaTeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
    private val seriaATeams =
        teamRepository.getSerieATeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
    private val ligaTeams =
        teamRepository.getLigaTeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
    private val premierLeagueTeams = teamRepository.getPremierLeagueTeams()
        .stateIn(viewModelScope, WhileUiSubscribed, emptyList())
    private val primeiraDivisionTeams = teamRepository.getPrimeiraDivisionTeams()
        .stateIn(viewModelScope, WhileUiSubscribed, emptyList())

    val uiState: StateFlow<SortedTeamsUiState> = combine(
        ligue1Teams,
        bundesligaTeams,
        seriaATeams,
        ligaTeams,
        premierLeagueTeams,
        primeiraDivisionTeams
    ) { input ->
        SortedTeamsUiState.Success(input.flatMap { it.orEmpty() })
    }.stateIn(viewModelScope, WhileUiSubscribed, SortedTeamsUiState.Loading)


}

sealed interface SortedTeamsUiState {
    data object Loading : SortedTeamsUiState
    data class Success(val teams:List<Team>): SortedTeamsUiState
}