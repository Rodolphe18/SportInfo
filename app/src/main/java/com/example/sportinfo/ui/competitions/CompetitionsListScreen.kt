package com.example.sportinfo.ui.competitions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportinfo.domain.model.Competition
import com.example.sportinfo.ui.composable.BigSportsInfoItem


@Composable
internal fun CompetitionsRoute(viewModel: CompetitionListViewModel = hiltViewModel(), onCompetitionClick:(String) -> Unit) {
    val competitionState = viewModel.state

    CompetitionsListScreen(competitionState, onCompetitionClick)
}

@Composable
fun CompetitionsListScreen(competitionsListState: CompetitionsListState, onCompetitionClick:(String) -> Unit) {
    if (competitionsListState.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(modifier = Modifier.padding(horizontal = 8.dp), columns = GridCells.Fixed(1), state = rememberLazyGridState(),
            verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(8.dp)) {
            items(items = competitionsListState.competitions) { competition ->
                BigSportsInfoItem(competition, { _, _ -> }, onCompetitionClick)

                 }
        }
    }
}