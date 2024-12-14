package com.example.sportinfo.ui.competitions.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportinfo.ui.competitions.matches.MatchItem
import com.example.sportinfo.ui.competitions.matches.MatchesPerDaySection
import com.example.sportinfo.util.DateTimeFormatter

@Composable
fun PagerMatchDay(viewmodel: PagerViewmodel = hiltViewModel()) {
    val dayMatchMatches = viewmodel.matches
    val matchDay = viewmodel.matchDay
    val pagerState = rememberPagerState(initialPage = matchDay, pageCount = { 40 })
    val dayOfWeeks = dayMatchMatches.map { DateTimeFormatter.getFormattedDate(it.utcDate.orEmpty()) }.distinct().reversed()
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            viewmodel.getMatchesForASpecificMatchDay(it)
        }
    }
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(Modifier.height(28.dp)) }
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "matchDay $page",
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.DarkGray
                    )
                }
            }
            item {
                for (date in dayOfWeeks) {
                    MatchesPerDaySection(
                        date,
                        dayMatchMatches.filter { DateTimeFormatter.getFormattedDate(it.utcDate.orEmpty()) == date }.sortedBy { it.utcDate })
                }
            }
        }
    }
}