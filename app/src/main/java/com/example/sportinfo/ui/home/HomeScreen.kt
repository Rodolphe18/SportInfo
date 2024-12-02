package com.example.sportinfo.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.sportinfo.domain.model.Area


@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = viewModel.homeState

    HomeScreen(
        homeState = homeState,
        modifier = modifier
    )
}


@Composable
internal fun HomeScreen(
    homeState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    if (homeState.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2), state = rememberLazyGridState()) {
            items(items = homeState.areas) { area ->
                if(!area.flag.isNullOrEmpty()) AreaItem(area = area)
            }
        }
    }
}

@Composable
fun AreaItem(area: Area, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .width(145.dp)
            .height(90.dp)
            .padding(horizontal = 10.dp)
    ) {
        Box(modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .border(BorderStroke(1.dp, Color.Blue))) {
            AsyncImage(
                modifier = modifier.aspectRatio(1f).clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(area.flag)
                    .build(),
                contentDescription = null
            )
        }
        Column() {
            Text(
                modifier = Modifier,
                text = area.parentArea ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Red,
            )
            Text(
                modifier = Modifier,
                text = area.name ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Blue,
            )
        }
    }
}

