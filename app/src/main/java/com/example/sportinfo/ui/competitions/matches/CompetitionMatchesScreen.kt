package com.example.sportinfo.ui.competitions.matches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.ui.composable.MatchItem
import com.example.sportinfo.ui.composable.ScheduledMatchItem
import com.example.sportinfo.ui.composable.SectionTitle
import com.example.sportinfo.util.DateTimeFormatter
import java.time.OffsetDateTime

@Composable
internal fun CompetitionMatchesScreen(
    viewModel: CompetitionMatchesViewmodel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val competitionMatchesState by viewModel.state.collectAsStateWithLifecycle()
    val competitionName = viewModel.competitionName
    val matchDay = viewModel.matchDay
    CompetitionMatchesScreen(competitionMatchesState!!, competitionName, matchDay, onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompetitionMatchesScreen(
    uiState: CompetitionMatchesUiState,
    competitionName: String,
    matchDay: Int,
    onBackClick: () -> Unit
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val state = rememberLazyListState()
    Scaffold(
        modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = competitionName,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
            }, navigationIcon = {
                Icon(
                    modifier = Modifier
                        .offset(x = 8.dp)
                        .clickable { onBackClick() },
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = null
                )
            }, scrollBehavior = topAppBarScrollBehavior)
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            state = state,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) { Text(
                    text = "MatchDay $matchDay",
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ) }
            }
            uiState.matches
                .groupBy { match -> OffsetDateTime.parse(match.utcDate.orEmpty()).dayOfYear }
                .toSortedMap()
                .forEach { (key, value) ->
                    val matches = value.distinct()
                item(key = key) {
                    SectionTitle(modifier = Modifier.background(Color(0xff9FBE5B).copy(alpha = 0.8f)), title = DateTimeFormatter.getFormattedDate(value.first().utcDate.orEmpty()), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                }
                items(matches) {
                    MatchesPerDaySection(matches.sortedBy { it.utcDate })
                }
            }
        }
    }
}

@Composable
fun MatchesPerDaySection(matches: Collection<Match>?) {
        if (matches?.size != 0) {
           Column {
                if (matches != null) {
                    for (match in matches) {
                        when (match.status) {
                            "TIMED" -> ScheduledMatchItem(match)
                            else -> MatchItem(match = match)
                        }
                    }
                }
            }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportInfoCenterAlignedTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        scrollBehavior = scrollBehavior
    )
}