package com.example.sportinfo.ui.competitions.pager

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.domain.repository.MatchRepository
import com.example.sportinfo.ui.competitions.matches.CompetitionMatchesRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagerViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val matchesRepository: MatchRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)

    private val competitionCode =
        savedStateHandle.toRoute<CompetitionMatchesRoute>().competitionCode

    val currentMatchDay = savedStateHandle.toRoute<CompetitionMatchesRoute>().matchDay

    var matchDay by mutableIntStateOf(currentMatchDay)

    private val _pageMatches = mutableStateMapOf<Int, Set<Match>?>()
    val pageMatches: SnapshotStateMap<Int, Set<Match>?> = _pageMatches


    init {
        loadInitialPages()
    }

    fun loadInitialPages() {
        getMatchesForCurrentMatchDay()
        getMatchesForPreviousMatchDay()
        getMatchesForNextMatchDay()
    }


    fun getMatchesForCurrentMatchDay() {
        viewModelScope.launch {
            matchesRepository.getCompetitionMatchList(competitionCode, matchDay)
                .collect { matches ->
                    _pageMatches[matchDay] =
                        matches?.sortedByDescending { it.utcDate }?.toSet()
                }
        }
    }

    fun getMatchesForPreviousMatchDay() {
        viewModelScope.launch {
            matchesRepository.getCompetitionMatchList(competitionCode, matchDay - 1)
                .collect { matches ->
                    _pageMatches[matchDay - 1] =
                        matches?.sortedByDescending { it.utcDate }?.toSet()
                }
        }
    }

    fun getMatchesForNextMatchDay() {
        viewModelScope.launch {
            matchesRepository.getCompetitionMatchList(competitionCode, matchDay + 1)
                .collect { matches ->
                    _pageMatches[matchDay + 1] =
                        matches?.sortedByDescending { it.utcDate }?.toSet()
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _pageMatches.clear()
    }
}
