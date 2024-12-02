package com.example.sportinfo.data.remote.dto.competitions

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportinfo.domain.model.CurrentSeason
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class NetworkCurrentSeason(
    val currentMatchday: Int,
    val endDate: String,
    @SerialName("id") @PrimaryKey val seasonId: Int,
    val startDate: String,
    @SerialName("winner") @Embedded val winner: NetworkWinner
)

fun NetworkCurrentSeason.asExternalModel() = CurrentSeason(currentMatchday, endDate, seasonId, startDate, winner.asExternalModel())
