package com.example.sportinfo.ui.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalFoundationApi
@Composable
fun TwoPansPager(
    modifier: Modifier = Modifier,
    page1: @Composable () -> Unit,
    page2: @Composable () -> Unit
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabItem = intArrayOf(0, 1)
    val pagerState = rememberPagerState(0) { 2 }
    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.scrollToPage(page = selectedTabIndex)
    }
    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }
    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(
            modifier = modifier.fillMaxWidth().background(Color(0xff9FBE5B).copy(alpha = 0.3f)), selectedTabIndex = selectedTabIndex
        ) {
            tabItem.forEachIndexed { index, _ ->
                Tab(
                    modifier = Modifier.background(Color(0xff9FBE5B).copy(0.3f)),
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = if (index == 0) "By Championship" else "All Teams",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    })
            }
        }
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { index ->
            if (index == 0) {
                page1()
            } else {
                page2()
            }
        }
    }
}