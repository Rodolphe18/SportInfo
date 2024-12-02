package com.example.sportinfo.domain.model

data class CurrentSeason(
    val currentMatchday: Int,
    val endDate: String,
    val currentSeasonId: Int,
    val startDate: String,
    val winner: Winner
)
