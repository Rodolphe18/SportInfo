package com.example.sportinfo.ui.competitions.pager

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.domain.repository.MatchRepository
import com.example.sportinfo.ui.competitions.matches.CompetitionMatchesRoute
import com.example.sportinfo.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagerViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val matchesRepository: MatchRepository
) : ViewModel() {

    private val competitionCode =
        savedStateHandle.toRoute<CompetitionMatchesRoute>().competitionCode

    val matchDay = savedStateHandle.toRoute<CompetitionMatchesRoute>().matchDay

    val competitionName = savedStateHandle.toRoute<CompetitionMatchesRoute>().competitionName

    private val _matches = mutableStateListOf<Match>()
    val matches: SnapshotStateList<Match> = _matches

    init {
        getMatchesForASpecificMatchDay(matchDay)
    }

    fun getMatchesForASpecificMatchDay(matchDay: Int) {
        viewModelScope.launch {
            matchesRepository.getCompetitionMatchList(competitionCode, matchDay).collectLatest { matches ->
                _matches.clear()
                _matches.addAll(matches.sortedByDescending { it.utcDate })
            }
        }
    }

}