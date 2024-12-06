package com.example.sportinfo.ui.teams

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportinfo.domain.model.Team
import com.example.sportinfo.ui.composable.SectionTitle
import com.example.sportinfo.ui.composable.SmallSportsInfoItem


@Composable
fun TeamRoute(viewModel: TeamsListViewModel = hiltViewModel()) {
    val teamState by viewModel.uiState.collectAsStateWithLifecycle()
    TeamsListScreen(teamState)
}

@Composable
fun TeamsListScreen(teamsUiState: TeamsUiState) {
    when(teamsUiState) {
        TeamsUiState.Loading -> CircularProgressIndicator()
        is TeamsUiState.Success ->
            LazyColumn {
                item {
                    val teamsType = enumValues<TeamType>()
                    for(teamType in teamsType) {
                        TeamsSection(teamType.title, teamsUiState.teams.filter { it.area?.name?.lowercase() == teamType.country.lowercase() })
                    } }
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
                    SmallSportsInfoItem(team, {_,_ ->})
                }
            }
        }
    }
}