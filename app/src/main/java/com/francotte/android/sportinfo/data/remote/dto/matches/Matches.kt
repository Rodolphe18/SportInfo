package com.francotte.android.sportinfo.data.remote.dto.matches

import com.francotte.android.sportinfo.data.remote.dto.competitions.NetworkLightCompetition
import kotlinx.serialization.Serializable

@Serializable
data class Matches(
    val competition: NetworkLightCompetition,
    val filters: Filters,
    val matches: List<Match>,
    val resultSet: ResultSet
)

