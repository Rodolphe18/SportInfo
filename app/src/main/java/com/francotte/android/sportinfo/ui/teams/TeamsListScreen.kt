package com.francotte.android.sportinfo.ui.teams

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.francotte.android.sportinfo.domain.model.Team
import com.francotte.android.sportinfo.ui.composable.BigTeamInfoItem
import com.francotte.android.sportinfo.ui.composable.LoadingScreen
import com.francotte.android.sportinfo.ui.composable.SectionTitle
import com.francotte.android.sportinfo.ui.composable.SmallTeamInfoItem
import com.francotte.android.sportinfo.ui.composable.TwoPansPager
import com.francotte.android.sportinfo.ui.theme.LocalGradientColors
import com.francotte.android.sportinfo.ui.theme.SportInfoGradientBackground


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamRoute(
    allTeamsViewModel: TeamsListViewModel = hiltViewModel(),
    sortedTeamsUiState: TeamsByChampionshipViewModel = hiltViewModel()
) {
    val sortedTeamsState by sortedTeamsUiState.uiState.collectAsStateWithLifecycle()
    SportInfoGradientBackground(gradientColors = LocalGradientColors.current) {

    TwoPansPager(
        page1 = {
            TeamsByChampionshipListScreen(sortedTeamsState)
             },
        page2 = { AllTeamsListScreen(allTeamsViewModel) })
}
}


@Composable
fun AllTeamsListScreen(
    viewModel: TeamsListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val shouldStartPaginate by remember {
        derivedStateOf {
            viewModel.canPaginate && (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (listState.layoutInfo.totalItemsCount - 6)
        }
    }
    LaunchedEffect(shouldStartPaginate) {
        if (shouldStartPaginate && viewModel.pageStatus == PageStatus.IDLE) {
            viewModel.getTeams()
        }
    }
    LazyColumn(state = listState, verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(8.dp)) {
        items(state.teams) { team ->
            SmallTeamInfoItem(team = team)
        }
        if (viewModel.pageStatus == PageStatus.PAGINATING) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }
        }
    }
}


@Composable
fun TeamsByChampionshipListScreen(state: SortedTeamsUiState) {
    when (state) {
        SortedTeamsUiState.Loading -> LoadingScreen()
        is SortedTeamsUiState.Success -> {
            val teams = state.teams
            val championships = enumValues<TeamType>()
            LazyColumn {
                item {
                    for (championship in championships) {
                        TeamsSection(
                            championship.title,
                            teams.filter { it.area?.name?.lowercase() == championship.country.lowercase() })
                    }
                }
                item { Spacer(Modifier.height(8.dp)) }
            }
        }
    }

}


@Composable
fun TeamsSection(
    title: String,
    teams: List<Team>?,
) {
    val listState = rememberLazyListState()
    Column(modifier = Modifier.padding(top = 10.dp)) {
        if (teams?.size != 0) {
            SectionTitle(title = title)
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = requireNotNull(teams),
                    key = { it.id }
                ) { team ->
                    BigTeamInfoItem(modifier = Modifier
                        .width(260.dp)
                        .aspectRatio(2f)
                        .clip(shape = RoundedCornerShape(16.dp)), team)
                }
            }
        }
    }
}