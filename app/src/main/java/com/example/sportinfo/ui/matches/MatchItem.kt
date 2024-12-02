package com.example.sportinfo.ui.matches

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportinfo.data.remote.dto.matches.Match

@Composable
fun MatchItem(match: Match, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.weight(1f),
                text = match.homeTeam.name,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Black,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = match.awayTeam.name,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Red,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.weight(1f),
                text = match.utcDate,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Green,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = match.lastUpdated,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Magenta,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = match.status,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.DarkGray,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.weight(1f),
                text = match.status,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.DarkGray,
            )
        }
    }
}