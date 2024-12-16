package com.example.sportinfo.ui.competitions.matches

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.sportinfo.data.remote.dto.matches.Match
import com.example.sportinfo.domain.model.Competition
import com.example.sportinfo.util.DateTimeFormatter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.Date
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
fun MatchItem(match: Match, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .padding(horizontal = 4.dp)
                    .weight(0.3f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(match.homeTeam.crest)
                        .build(),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.weight(1.5f),
                text = match.homeTeam.name.orEmpty(),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
            )
            Text(
                modifier = Modifier.weight(0.2f),
                text = match.score?.fullTime?.home.toString(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .padding(horizontal = 4.dp)
                    .weight(0.3f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(match.awayTeam.crest)
                        .build(),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.weight(1.5f),
                text = match.awayTeam.name,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
            )
            Text(
                modifier = Modifier.weight(0.2f),
                text = match.score?.fullTime?.away.toString(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
            )
        }
    }
}

@Composable
fun ScheduledMatchItem(match: Match, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 4.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(match.homeTeam.crest)
                            .build(),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 18.dp),
                    text = match.homeTeam.name.orEmpty(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 4.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(match.awayTeam.crest)
                            .build(),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 18.dp),
                    text = match.awayTeam.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                )
            }
        }
        Text(modifier = Modifier.weight(0.2f),text = DateTimeFormatter.getFormattedTime(match.utcDate.orEmpty()), fontWeight = FontWeight.SemiBold)
    }
}