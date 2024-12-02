package com.example.sportinfo.data.remote.dto.competitions

import androidx.room.ColumnInfo
import com.example.sportinfo.data.local.model.CompetitionEntity
import com.example.sportinfo.data.remote.dto.areas.NetworkArea
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("competition")
@Serializable
data class NetworkCompetition(
    val area: NetworkArea,
    val code: String,
    val currentSeason: NetworkCurrentSeason,
    val emblem: String,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val seasons: List<NetworkSeason>,
    val type: String
)

@Serializable
data class NetworkLightCompetition(
    val code: String,
    val emblem: String,
    @ColumnInfo("competitionId") val id: Int,
    val name: String,
    val type: String
)

fun NetworkCompetition.asEntity() = CompetitionEntity(area, code, currentSeason, emblem, id, lastUpdated, name, type)




