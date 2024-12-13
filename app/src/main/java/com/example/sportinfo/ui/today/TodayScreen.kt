package com.example.sportinfo.ui.today

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.ui.competitions.matches.MatchItem
import com.example.sportinfo.ui.competitions.matches.ScheduledMatchItem
import com.example.sportinfo.ui.composable.SectionTitle
import com.example.sportinfo.ui.teams.TeamType

@Composable
internal fun TodayRoute(viewModel: TodayViewModel = hiltViewModel()) {
    val todayState by viewModel.state.collectAsStateWithLifecycle()
    TodayScreen(todayState)
}

@Composable
fun TodayScreen(uiState: TodayUiState) {
    when (uiState) {
        TodayUiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }

        is TodayUiState.Success -> LazyColumn {
            item {
                val teamsType = enumValues<TeamType>()
                for (teamType in teamsType) {
                    TodaySection(
                        teamType.title,
                        uiState.matches.filter { it.area.name?.lowercase() == teamType.country.lowercase() })
                }
            }
        }
    }
}

@Composable
fun TodaySection(
    title: String,
    matches: List<Match>?,
) {
    Column(modifier = Modifier.padding(top = 10.dp)) {
        if (matches?.size != 0) {
            SectionTitle(title = title)
            Column {
                if (matches != null) {
                    for (match in matches) {
                        when (match.status) {
                            "TIMED" -> ScheduledMatchItem(match)
                            else -> MatchItem(match)
                        }
                    }
                }
            }
        }
    }
}