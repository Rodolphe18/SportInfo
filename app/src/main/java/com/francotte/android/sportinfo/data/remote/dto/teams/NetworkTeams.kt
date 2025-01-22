package com.francotte.android.sportinfo.data.remote.dto.teams


import com.francotte.android.sportinfo.data.remote.dto.competitions.NetworkLightCompetition
import com.francotte.android.sportinfo.data.remote.dto.competitions.NetworkSeason
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTeams(
    val teams: List<NetworkTeam>,
    val count: Int,
    val filters: Filters
)

@Serializable
class Filters

@Serializable
data class NetworkTeamsByCompetition(
    val teams: List<NetworkTeam>,
    val competition: NetworkLightCompetition,
    val season: NetworkSeason,
    val count: Int,
    val filters: Filters
)

