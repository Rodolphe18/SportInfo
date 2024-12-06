package com.example.sportinfo.data.remote.dto.competitions

import com.example.sportinfo.data.remote.dto.areas.NetworkArea
import com.example.sportinfo.data.remote.dto.areas.asExternalModel
import com.example.sportinfo.domain.model.Competition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("competition")
@Serializable
data class NetworkCompetition(
    val area: NetworkArea?,
    val code: String? = "",
    val currentSeason: NetworkCurrentSeason?,
    val emblem: String? = "",
    val id: Int,
    val lastUpdated: String? = "",
    val name: String? = "",
    val seasons: List<NetworkSeason>?,
    val type: String? = ""
)

@Serializable
data class NetworkLightCompetition(
    val code: String? = "",
    val emblem: String? = "",
    val id: Int,
    val name: String? = "",
    val type: String? = ""
)

fun NetworkCompetition.asExternalModel() = Competition(area?.asExternalModel(), code.orEmpty(), currentSeason?.asExternalModel(), emblem.orEmpty(), id, lastUpdated.orEmpty(), name.orEmpty(), seasons?.map { it.asExternalModel() }, type.orEmpty())

// fun NetworkCompetition.asEntity() = CompetitionEntity(area, code.orEmpty(), currentSeason, emblem.orEmpty(), id, lastUpdated.orEmpty(), name.orEmpty(), type.orEmpty())




