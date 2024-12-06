package com.example.sportinfo.data.remote.dto.competitions

import com.example.sportinfo.domain.model.CurrentSeason
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetworkCurrentSeason(
    val currentMatchday: Int,
    val endDate: String? = null,
    @SerialName("id") val seasonId: Int,
    val startDate: String ? = null,
    @SerialName("winner") val winner: NetworkWinner?
)

fun NetworkCurrentSeason.asExternalModel() = CurrentSeason(currentMatchday, endDate.orEmpty(), seasonId, startDate.orEmpty(), winner?.asExternalModel())
