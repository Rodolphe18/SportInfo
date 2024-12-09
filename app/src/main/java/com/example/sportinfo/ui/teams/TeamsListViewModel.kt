package com.example.sportinfo.ui.teams

import android.graphics.pdf.PdfDocument.Page
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.domain.model.Team
import com.example.sportinfo.domain.repository.TeamRepository
import com.example.sportinfo.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
class TeamsListViewModel @Inject constructor(private val teamRepository: TeamRepository) :
    ViewModel() {


//    private val ligue1Teams =
//        teamRepository.getLigue1Teams().stateIn(viewModelScope, WhileUiSubscribed, null)
//    private val bundesligaTeams =
//        teamRepository.getBundesligaTeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
//    private val seriaATeams =
//        teamRepository.getSerieATeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
//    private val ligaTeams =
//        teamRepository.getLigaTeams().stateIn(viewModelScope, WhileUiSubscribed, emptyList())
//    private val premierLeagueTeams = teamRepository.getPremierLeagueTeams()
//        .stateIn(viewModelScope, WhileUiSubscribed, emptyList())
//    private val primeiraDivisionTeams = teamRepository.getPrimeiraDivisionTeams()
//        .stateIn(viewModelScope, WhileUiSubscribed, emptyList())
//
//    val uiState: StateFlow<TeamsUiState> = combine(
//        ligue1Teams,
//        bundesligaTeams,
//        seriaATeams,
//        ligaTeams,
//        premierLeagueTeams,
//        primeiraDivisionTeams
//    ) { input ->
//        TeamsUiState.Success(input.flatMap { it.orEmpty() })
//    }.stateIn(viewModelScope, WhileUiSubscribed, TeamsUiState.Loading)

    val _state = MutableStateFlow(TeamsUiState(emptyList()))
    val state = _state.stateIn(viewModelScope, WhileUiSubscribed, TeamsUiState(emptyList()))

    private var page by mutableIntStateOf(1)
    var pageStatus by mutableStateOf(PageStatus.IDLE)
    var canPaginate by mutableStateOf(false)

    init {
        getTeams()

    }

    fun getTeams() {
        viewModelScope.launch {
            if (page == 1 || (page != 1 && canPaginate) && pageStatus == PageStatus.IDLE) {
                pageStatus = if (page == 1) PageStatus.LOADING else PageStatus.PAGINATING
                teamRepository
                    .getTeamsList(if (page == 1) 0 else page * 20 - 20)
                    .collect {
                        canPaginate = it.size == 20
                        _state.updateAndGet { existingItems ->
                            val newList = existingItems.teams.plus(it)
                            TeamsUiState(teams = newList)
                        }
                        pageStatus = PageStatus.IDLE
                        if (canPaginate) page++ else pageStatus =
                            if (page == 1) PageStatus.ERROR else PageStatus.PAGINATION_EXHAUSTED
                    }
            }
        }
    }

    override fun onCleared() {
        page = 1
        pageStatus = PageStatus.IDLE
        canPaginate = false
        super.onCleared()
    }
}


data class TeamsUiState(val teams: List<Team>)



enum class TeamType(val title: String, val country: String) {
    LIGUE_1(
        "Ligue 1",
        "France"
    ),
    BUNDESLIGA("Bundesliga", "Germany"), SERIA_A("Serie A", "Italy"), LIGA(
        "Liga",
        "Spain"
    ),
    PREMIER_LEAGUE("Premier League", "England"), PRIMEIRA_DIVISION("Primeira Division", "Portugal")
}

enum class PageStatus { IDLE, LOADING, PAGINATING, ERROR, PAGINATION_EXHAUSTED }
