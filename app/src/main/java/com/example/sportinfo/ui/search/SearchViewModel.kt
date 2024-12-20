package com.example.sportinfo.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportinfo.domain.model.Competition
import com.example.sportinfo.domain.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchContentsRepository: SearchContentsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResultUiState: StateFlow<SearchResultUiState> =
        searchQuery.flatMapLatest { query ->
            if (query.length < SEARCH_QUERY_MIN_LENGTH) {
                flowOf(SearchResultUiState.EmptyQuery)
            } else {
            searchContentsRepository.searchContents(query)
               .map<SearchResult, SearchResultUiState> { data ->
                    SearchResultUiState.Success(
                        teams = data.teams,
                        competitions = data.competitions,
                    )
                }
                .catch { emit(SearchResultUiState.LoadFailed) }
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchResultUiState.Loading,
            )

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }



}


private const val SEARCH_QUERY_MIN_LENGTH = 2
private const val SEARCH_QUERY = "searchQuery"

sealed interface SearchResultUiState {
    data object Loading : SearchResultUiState
    data object EmptyQuery : SearchResultUiState
    data object LoadFailed : SearchResultUiState
    data class Success(
        val teams: List<Team> = emptyList(),
        val competitions: List<Competition> = emptyList(),
    ) : SearchResultUiState {
        fun isEmpty(): Boolean = teams.isEmpty() && competitions.isEmpty()
    }
    data object SearchNotReady : SearchResultUiState
}

sealed interface RecentSearchQueriesUiState {
    data object Loading : RecentSearchQueriesUiState

    data class Success(
        val recentQueries: List<RecentSearchQuery> = emptyList(),
    ) : RecentSearchQueriesUiState
}

data class RecentSearchQuery(
    val query: String,
    val queriedDate: Instant = Clock.System.now(),
)