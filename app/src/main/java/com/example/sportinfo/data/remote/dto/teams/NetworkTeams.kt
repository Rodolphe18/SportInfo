package com.example.sportinfo.data.remote.dto.teams

import com.example.sportinfo.data.remote.dto.competitions.NetworkCompetition
import com.example.sportinfo.data.remote.dto.competitions.NetworkLightCompetition
import com.example.sportinfo.data.remote.dto.competitions.NetworkSeason
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

