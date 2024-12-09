package com.example.sportinfo.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.francotte.android.sportinfo.R

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onToggleFavorite: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(5.dp)
            .size(40.dp)
            .clip(CircleShape)
            .drawBehind { drawCircle(color = Color.White) }
            .toggleable(
                value = isFavorite,
                onValueChange = {
                    onToggleFavorite(it)
                }
            )

    ) {
        Icon(
            painter = if (isFavorite) painterResource(R.drawable.ic_favorite_full) else painterResource(
                R.drawable.ic_favorite_empty
            ),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center),
            tint = if (isFavorite) Color.Yellow else Color.LightGray
        )
    }
}