package com.example.sportinfo.ui.teams

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportinfo.domain.model.Team
import com.example.sportinfo.ui.composable.SectionTitle
import com.example.sportinfo.ui.composable.SmallTeamsInfoItem


@Composable
fun TeamRoute(viewModel: TeamsListViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TeamsListScreen(
        viewModel
    )
}

@Composable
fun TeamsListScreen(
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
    LazyColumn(state = listState) {
        items(state.teams) {
            SmallTeamsInfoItem(it) { _, _ -> }
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
fun TeamsSection(
    title: String,
    teams: List<Team>?,
) {
    val listState = rememberLazyListState()
    Column(modifier = Modifier.padding(top = 10.dp)) {
        if (teams?.size != 0) {
            SectionTitle(title)
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = requireNotNull(teams),
                    key = { it.id }
                ) { team ->
                    SmallTeamsInfoItem(team, { _, _ -> })
                }
            }
        }
    }
}