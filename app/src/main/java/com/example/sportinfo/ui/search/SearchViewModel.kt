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
    //recentSearchQueriesUseCase: GetRecentSearchQueriesUseCase,
    searchContentsRepository: SearchContentsRepository,
   // private val recentSearchRepository: RecentSearchRepository,
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
                // Not using .asResult() here, because it emits Loading state every
                // time the user types a letter in the search box, which flickers the screen.
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

//    val recentSearchQueriesUiState: StateFlow<RecentSearchQueriesUiState> =
//        recentSearchQueriesUseCase()
//            .map(RecentSearchQueriesUiState::Success)
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = RecentSearchQueriesUiState.Loading,
//            )

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    /**
     * Called when the search action is explicitly triggered by the user. For example, when the
     * search icon is tapped in the IME or when the enter key is pressed in the search text field.
     *
     * The search results are displayed on the fly as the user types, but to explicitly save the
     * search query in the search text field, defining this method.
     */
//    fun onSearchTriggered(query: String) {
//        viewModelScope.launch {
//            recentSearchRepository.insertOrReplaceRecentSearch(searchQuery = query)
//        }
//    }
//
//    fun clearRecentSearches() {
//        viewModelScope.launch {
//            recentSearchRepository.clearRecentSearches()
//        }
//    }

}


/** Minimum length where search query is considered as [SearchResultUiState.EmptyQuery] */
private const val SEARCH_QUERY_MIN_LENGTH = 2

/** Minimum number of the fts table's entity count where it's considered as search is not ready */
private const val SEARCH_MIN_FTS_ENTITY_COUNT = 1
private const val SEARCH_QUERY = "searchQuery"

sealed interface SearchResultUiState {
    data object Loading : SearchResultUiState

    /**
     * The state query is empty or too short. To distinguish the state between the
     * (initial state or when the search query is cleared) vs the state where no search
     * result is returned, explicitly define the empty query state.
     */
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