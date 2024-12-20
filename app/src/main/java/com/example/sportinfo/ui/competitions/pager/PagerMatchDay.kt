package com.example.sportinfo.ui.competitions.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportinfo.ui.competitions.matches.MatchesPerDaySection
import com.example.sportinfo.ui.composable.LoadingScreen
import com.example.sportinfo.ui.composable.SectionTitle
import com.example.sportinfo.util.DateTimeFormatter
import okhttp3.internal.toImmutableMap
import java.time.OffsetDateTime

@Composable
fun PagerMatchDay(viewmodel: PagerViewmodel = hiltViewModel()) {
    val matchesPerMatchDay = viewmodel.pageMatches.toImmutableMap()
    val matchDay = viewmodel.matchDay
    val pagerState = rememberPagerState(initialPage = matchDay, pageCount = { 40 })
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { newPage ->
            if(newPage < viewmodel.matchDay ) {
                viewmodel.matchDay = newPage
            viewmodel.getMatchesForPreviousMatchDay()
        } else {
            viewmodel.matchDay = newPage
            viewmodel.getMatchesForNextMatchDay()} }
    }
    HorizontalPager(
        state = pagerState,
        beyondViewportPageCount = 1,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        val matchesPerDay = matchesPerMatchDay[index]?.distinct()
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(Modifier.height(28.dp)) }
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "matchDay $index",
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            matchesPerDay
                ?.groupBy { match -> OffsetDateTime.parse(match.utcDate.orEmpty()).dayOfWeek }
                ?.toSortedMap()
                ?.forEach { (key, matches) ->
                    item(key = key) {
                        SectionTitle(
                            modifier = Modifier.background(Color(0xff9FBE5B).copy(alpha = 0.8f)),
                            title = DateTimeFormatter.getFormattedDate(matches.first().utcDate.orEmpty()),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                    item {
                        MatchesPerDaySection(matches.sortedBy { it.utcDate })
                    }
                }
        }
    }}


