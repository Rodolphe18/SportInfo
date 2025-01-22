package com.francotte.android.sportinfo.data.remote.dto.teams

import com.francotte.android.sportinfo.data.remote.dto.areas.NetworkArea
import com.francotte.android.sportinfo.data.remote.dto.areas.asExternalModel
import com.francotte.android.sportinfo.domain.model.Team
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class NetworkTeam(
    val address: String? = "",
    val area: NetworkArea?,
    val clubColors: String? = "",
    val coach: NetworkCoach?,
    val crest: String? = "",
    val founded: Int,
    val id: Int,
    val lastUpdated: String? = "",
    val marketValue: Int,
    val name: String? = "",
    val runningCompetitions: List<NetworkRunningCompetition?>? = emptyList(),
    val shortName: String? = "",
    val squad: List<NetworkSquad?>? = emptyList(),
    @Transient val staff: List<Any> = emptyList(),
    val tla: String? = "",
    val venue: String? = "",
    val website: String? = ""
)

fun NetworkTeam.asExternalModel() = Team(address, area?.asExternalModel(), clubColors, coach?.asExternalModel(), crest, founded, id, lastUpdated, marketValue, name, runningCompetitions?.map { it?.asExternalModel() }.orEmpty(), shortName, squad?.map { it?.asExternalModel() }.orEmpty(), tla, venue, website)
