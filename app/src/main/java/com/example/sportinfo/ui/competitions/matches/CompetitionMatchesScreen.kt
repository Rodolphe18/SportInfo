package com.example.sportinfo.ui.competitions.matches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.domain.model.Competition
import com.example.sportinfo.ui.composable.SectionTitle

@Composable
internal fun CompetitionMatchesScreen(
    viewModel: CompetitionMatchesViewmodel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val competitionMatchesState by viewModel.state.collectAsStateWithLifecycle()
    CompetitionMatchesScreen(competitionMatchesState, onBackClick)
}

@Composable
fun CompetitionMatchesScreen(uiState: CompetitionMatchesUiState, onBackClick: () -> Unit) {
    LazyColumn {
        item {
         //   CompetitionMatchesToolbar()
        }
        items(uiState.matches) {
            MatchItem(it)
        }

    }
}

@Composable
fun CompetitionMatchesSection(
    title: String,
    matches: List<Match>?,
) {
    Column(modifier = Modifier.padding(top = 10.dp)) {
        if (matches?.size != 0) {
            SectionTitle(title)
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

@Composable
private fun CompetitionMatchesToolbar(
    competition: Competition,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
    ) {

        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = null,
            )
        }

        Text(text = competition.name.orEmpty())
    }
}