package com.francotte.android.sportinfo.data.remote.dto.competitions

import com.francotte.android.sportinfo.data.remote.dto.competitions.NetworkCompetition
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCompetitions(
    val competitions: List<NetworkCompetition>,
    val count: Int,
    val filters: Filters
)

@Serializable
class Filters

