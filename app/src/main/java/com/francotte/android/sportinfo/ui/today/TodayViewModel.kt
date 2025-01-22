package com.francotte.android.sportinfo.ui.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francotte.android.sportinfo.data.remote.dto.matches.Match
import com.francotte.android.sportinfo.domain.repository.MatchRepository
import com.francotte.android.sportinfo.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(matchRepository: MatchRepository) : ViewModel() {

    val state: StateFlow<TodayUiState> = matchRepository
        .getTodayMatchList().map { TodayUiState.Success(it) }.stateIn(viewModelScope, WhileUiSubscribed,
            TodayUiState.Loading
        )

}


sealed interface TodayUiState {

    data object Loading : TodayUiState
    data class Success(
        val matches:List<Match>
    ) : TodayUiState
}