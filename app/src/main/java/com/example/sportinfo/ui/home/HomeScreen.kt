package com.example.sportinfo.ui.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.R
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sportinfo.domain.model.Area


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.homeState.collectAsStateWithLifecycle()
    HomeScreen(homeState = homeUiState)
}


@Composable
internal fun HomeScreen(homeState: HomeUiState) {
    if (homeState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.Blue)
        }
    } else {
        LazyColumn(state = rememberLazyListState()) {
            item {
                for (type in enumValues<ContinentArea>()) {
                    homeState.areas
                        ?.asSequence()
                        ?.filter { area -> !area.flag.isNullOrEmpty() }
                        .let { areaList ->
                        val section = areaList?.filter {
                            it.parentArea?.lowercase().orEmpty() == type.title.lowercase()
                        }?.toList()
                        AreasSection(type.title, section)
                    }
                }
            }
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
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
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
            .height(90.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(area.flag)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
        }
        Column(Modifier.padding(horizontal = 12.dp)) {
            Text(
                text = area.parentArea?.uppercase().orEmpty(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.DarkGray,
            )
            Text(
                text = area.name.orEmpty(),
                fontWeight = FontWeight.Bold,
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
            androidx.compose.material3.Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colors.primary
            )
        }
    }
}
