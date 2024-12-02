package com.example.sportinfo.ui.matches

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.domain.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesListViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    var state by mutableStateOf(MatchesListState())

    init {
        viewModelScope.launch {
            repository
                .getRemoteMatchList()
                .collect { competitionList ->
                    competitionList.let { list ->
                        state = state.copy(matches = list)
                    }
                }
        }
    }
}

data class MatchesListState(
    val matches: List<Match> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
