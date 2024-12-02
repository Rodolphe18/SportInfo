package com.example.sportinfo.domain.model


data class Competition(
    val area: Area,
    val code: String,
    val currentSeason: CurrentSeason,
    val emblem: String,
    val id : Int?,
    val lastUpdated: String,
    val name: String,
    val type: String
)

