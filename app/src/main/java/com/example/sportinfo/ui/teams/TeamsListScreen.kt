package com.example.sportinfo.ui.teams

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportinfo.domain.model.Team
import com.example.sportinfo.ui.composable.BigTeamsInfoItem
import com.example.sportinfo.ui.composable.LoadingScreen
import com.example.sportinfo.ui.composable.SectionTitle
import com.example.sportinfo.ui.composable.SmallTeamsInfoItem
import com.example.sportinfo.ui.composable.TwoPansPager


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamRoute(allTeamsViewModel: TeamsListViewModel = hiltViewModel(), sortedTeamsUiState: TeamsByChampionshipViewModel = hiltViewModel()) {
    val sortedTeamsState by sortedTeamsUiState.uiState.collectAsStateWithLifecycle()
    TwoPansPager(page1 = { AllTeamsListScreen(allTeamsViewModel) }, page2 = { TeamsByChampionshipListScreen(sortedTeamsState) })
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
    LazyColumn(state = listState, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items(state.teams) { team ->
            SmallTeamsInfoItem(modifier = Modifier.fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 12.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(Color.White), team = team) { _, _ -> }
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
fun TeamsByChampionshipListScreen(state:SortedTeamsUiState) {
    when(state) {
        SortedTeamsUiState.Loading -> LoadingScreen()
        is SortedTeamsUiState.Success -> {
            val teams = state.teams
            val championships = enumValues<TeamType>()
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xff9FBE5B),
                                Color(0xffDFFF00)
                            )
                        )
                    )
            )
            LazyColumn() {
                item {
                    for(championship in championships) {
                        TeamsSection(championship.title, teams.filter { it.area?.name?.lowercase() == championship.country.lowercase() })
                    }
                }
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
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = requireNotNull(teams),
                    key = { it.id }
                ) { team ->
                    BigTeamsInfoItem(modifier = Modifier
                        .width(280.dp)
                        .aspectRatio(2f)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(Color.White), team, { _, _ -> })
                }
            }
        }
    }
}