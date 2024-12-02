package com.example.sportinfo.ui.competitions

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
internal fun CompetitionRoute(
    modifier: Modifier = Modifier,
    viewModel: CompetitionListViewModel = hiltViewModel()
) {
    val competitionState = viewModel.state

    CompetitionsListScreen(competitionState)
}

@Composable
fun CompetitionsListScreen(competitionsListState: CompetitionsListState) {
    if (competitionsListState.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(1), state = rememberLazyGridState()) {
            items(items = competitionsListState.competitions) { competition ->
                CompetitionItem(competition = competition)
            }
        }
    }
}