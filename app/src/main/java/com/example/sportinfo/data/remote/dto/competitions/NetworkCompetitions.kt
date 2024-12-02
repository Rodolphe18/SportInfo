package com.example.sportinfo.data.remote.dto.competitions


import kotlinx.serialization.Serializable

@Serializable
data class NetworkCompetitions(
    val competitions: List<NetworkCompetition>,
    val count: Int,
    val filters: Filters
)

@Serializable
class Filters

