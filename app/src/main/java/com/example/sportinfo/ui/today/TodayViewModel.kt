package com.example.sportinfo.ui.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.domain.repository.MatchRepository
import com.example.sportinfo.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(matchRepository: MatchRepository) : ViewModel() {

    val state: StateFlow<TodayUiState> = matchRepository
        .getTodayMatchList().map { TodayUiState.Success(it) }.stateIn(viewModelScope, WhileUiSubscribed, TodayUiState.Loading)

}


sealed interface TodayUiState {

    data object Loading : TodayUiState
    data class Success(
        val matches:List<Match>
    ) : TodayUiState
}