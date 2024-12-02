package com.example.sportinfo.ui.competitions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sportinfo.domain.model.Competition

@Composable
fun CompetitionItem(competition: Competition, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .size(70.dp)
            .padding(horizontal = 4.dp)
            .weight(0.5f)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(competition.emblem)
                    .build(),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.weight(1.3f),
            text = competition.name,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
        )
        Text(
            modifier = Modifier.padding(end = 6.dp),
            text = competition.area.name.orEmpty(),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}