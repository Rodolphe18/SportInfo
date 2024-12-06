package com.example.sportinfo.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.domain.model.Team
import com.example.sportinfo.domain.repository.TeamRepository
import com.example.sportinfo.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TeamsListViewModel @Inject constructor(teamRepository: TeamRepository) : ViewModel() {

    private val ligue1Teams = teamRepository.getLigue1Teams().stateIn(viewModelScope, WhileUiSubscribed, null)
    private val bundesligaTeams = teamRepository.getBundesligaTeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
    private val seriaATeams = teamRepository.getSerieATeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
    private val ligaTeams = teamRepository.getLigaTeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
    private val premierLeagueTeams = teamRepository.getPremierLeagueTeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
    private val primeiraDivisionTeams = teamRepository.getPrimeiraDivisionTeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())

    val uiState: StateFlow<TeamsUiState> = combine(
        ligue1Teams, bundesligaTeams, seriaATeams, ligaTeams, premierLeagueTeams, primeiraDivisionTeams
    ) { input -> TeamsUiState.Success(input.flatMap { it.orEmpty() })
    }.stateIn(viewModelScope, WhileUiSubscribed, TeamsUiState.Loading)

}


sealed interface TeamsUiState {

    object Loading : TeamsUiState
    data class Success(
        val teams:List<Team>
    ) : TeamsUiState
}

enum class TeamType(val title:String, val country:String) { LIGUE_1("Ligue 1", "France"), BUNDESLIGA("Bundesliga", "Germany"), SERIA_A("Serie A", "Italy"), LIGA ("Liga", "Spain"), PREMIER_LEAGUE("Premier League", "England"), PRIMEIRA_DIVISION("Primeira Division", "Portugal") }
