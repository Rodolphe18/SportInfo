package com.example.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable

@Serializable
data class Odds(
    val awayWin: Double,
    val draw: Double,
    val homeWin: Double
)