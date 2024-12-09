package com.example.sportinfo.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sportinfo.domain.model.Competition
import com.example.sportinfo.domain.model.Team
import com.example.sportinfo.util.DateTimeFormatter
import com.example.sportinfo.util.NumberFormatter
import com.francotte.android.sportinfo.R


@Composable
fun SmallTeamsInfoItem(team: Team, onToggleFavorite: (Team, Boolean) -> Unit) {
    Box(Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .clip(shape = RoundedCornerShape(6.dp))
        .background(Color(0xFFBDDBFE))
        ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier.size(25.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(team.crest)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = team.name.orEmpty(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            }
            Text(
                text = team.venue.orEmpty(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = team.area?.name.orEmpty(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = team.runningCompetitions.map { it?.name }.joinToString(" | "),
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
        FavoriteButton(modifier = Modifier
            .align(Alignment.TopEnd).padding(4.dp),isFavorite = team.isFavorite, onToggleFavorite = { checked -> onToggleFavorite(team, checked) })

    }
}

@Composable
fun BigTeamsInfoItem(team: Team, onToggleFavorite: (Team, Boolean) -> Unit) {
    Box(Modifier
        .width(320.dp)
        .aspectRatio(2f)
        .clip(shape = RoundedCornerShape(16.dp))
        .background(Color(0xFFBDDBFE))) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier.size(25.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(team.crest)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = team.name.orEmpty(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            }
            Text(
                text = team.venue.orEmpty(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = team.area?.name.orEmpty(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
        FavoriteButton(modifier = Modifier
            .align(Alignment.TopEnd).padding(4.dp),isFavorite = team.isFavorite, onToggleFavorite = { checked -> onToggleFavorite(team, checked) })

    }
}

@Composable
fun BigSportsInfoItem(competition: Competition, onToggleFavorite: (Competition, Boolean) -> Unit, onCompetitionClick:(String) -> Unit) {
    Box(Modifier.height(150.dp).clip(shape = RoundedCornerShape(16.dp)).background(Color(0xFFBDDBFE)).clickable { onCompetitionClick(competition.code.orEmpty()) }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier.size(40.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(competition.emblem)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = competition.name.orEmpty(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Text(
                text = competition.area?.name?.uppercase().orEmpty(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = NumberFormatter.getFormattedDay(competition.currentSeason?.currentMatchday ?: 0),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            SportsInfoMetaData(competition)
        }
        FavoriteButton(modifier = Modifier
            .align(Alignment.BottomEnd).padding(4.dp),isFavorite = competition.isFavorite, onToggleFavorite = { checked -> onToggleFavorite(competition, checked) })
    }
}

@Composable
fun SportsInfoMetaData(
    competition: Competition
) {
    val date = DateTimeFormatter.getFormattedDate(competition.lastUpdated.orEmpty()).lowercase()
    val type = competition.type.orEmpty()

    Text(
        modifier = Modifier.padding(vertical = 2.dp),
        text = stringResource(R.string.card_meta_data_text, type, date),
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        maxLines = 1,
    )
}

