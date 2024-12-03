package com.example.sportinfo.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sportinfo.domain.model.Area
import com.example.sportinfo.ui.composable.HomePageChip
import kotlinx.coroutines.launch


@Composable
internal fun HomeRoute(
) {
    HomeScreen()
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val state by viewModel.homeState.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isReloading,
        onRefresh = viewModel::reload
    )
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.Blue)
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(state = pullRefreshState)
        ) {
            Column {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    item {
                        val chips = enumValues<HomeChipType>()
                        for (chip in chips) {
                            HomePageChip(
                                chipName = chip.title,
                                isSelected = chip.isSelected.value,
                                onSelectedCategoryChanged = {
                                    scope.launch {
                                        chips.onEach { it.isSelected.value = false }
                                        viewModel.getFilterSelected(chip)
                                        chip.isSelected.value = true
                                    }
                                },
                            )
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                if (!viewModel.isFilterSelected) {
                    LazyColumn(state = rememberLazyListState()) {
                        item {
                            for (type in enumValues<HomeChipType>()) {
                                state.areas
                                    ?.asSequence()
                                    ?.sortedByDescending { area -> !area.flag.isNullOrEmpty() }
                                    .let { areaList ->
                                        val section = areaList?.filter {
                                            it.parentAreaId == type.areaId
                                        }?.toList()
                                        AreasSection(type.title, section)
                                    }
                            }
                        }
                    }
                } else {
                    LazyColumn(state = rememberLazyListState()) {
                        state.areas
                            ?.sortedByDescending { area -> !area.flag.isNullOrEmpty() }
                            ?.let { areas ->
                                items(areas) { area ->
                                    AreaItem(area)
                                }
                            }

                    }
                }
            }
            PullRefreshIndicator(
                refreshing = state.isReloading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                contentColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}


@Composable
fun AreasSection(
    title: String,
    items: List<Area>?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 10.dp)) {
        if (!items.isNullOrEmpty()) {
            SectionTitle(title)
            LazyRow(
                state = rememberLazyListState(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = items,
                    key = { it.name + it.id }
                ) { area -> AreaItem(area = area) }
            }
        }
    }
}


@Composable
fun AreaItem(area: Area) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(75.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .width(80.dp)
                .height(50.dp)
                .background(Color.LightGray)
        ) {
            if (!area.flag.isNullOrEmpty()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(area.flag)
                        .build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
            }
        }
        Column(Modifier.padding(horizontal = 8.dp)) {
            Text(
                text = area.name.orEmpty(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Black,
            )
            Text(
                text = area.parentAreaId.toString(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Black,
            )
        }
    }
}


@Composable
fun SectionTitle(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
