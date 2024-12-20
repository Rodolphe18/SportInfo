package com.example.sportinfo.ui.competitions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportinfo.ui.composable.CompetitionItem
import com.example.sportinfo.ui.theme.LocalGradientColors
import com.example.sportinfo.ui.theme.SportInfoGradientBackground


@Composable
internal fun CompetitionsRoute(
    viewModel: CompetitionListViewModel = hiltViewModel(),
    onCompetitionClick: (String, String, Int) -> Unit
) {
    val competitionState = viewModel.state

    SportInfoGradientBackground(gradientColors = LocalGradientColors.current) {
        CompetitionsListScreen(competitionState, onCompetitionClick)
    }
}

@Composable
fun CompetitionsListScreen(
    competitionsListState: CompetitionsListState,
    onCompetitionClick: (String, String, Int) -> Unit
) {
    if (competitionsListState.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 4.dp),
            columns = GridCells.Fixed(1),
            state = rememberLazyGridState(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items = competitionsListState.competitions) { competition ->
                CompetitionItem(competition = competition, onCompetitionClick = onCompetitionClick)
            }
        }
    }
}