package com.example.sportinfo.ui.matches

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MatchRoute(modifier: Modifier = Modifier, viewModel: MatchesListViewModel = hiltViewModel()) {
    val matchState = viewModel.state
    MatchesListScreen(matchState)
}

@Composable
fun MatchesListScreen(matchesListState: MatchesListState) {
    if (matchesListState.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(1), state = rememberLazyGridState()) {
            items(items = matchesListState.matches) { match ->
                MatchItem(match = match)
            }
        }
    }
}