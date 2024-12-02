package com.example.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable

@Serializable
data class Filters(
    val matchday: String,
    val season: String,
    val dateFrom : String,
    val dateTo : String,
    val stage : String,
    val status : String,
    val group : String,
)


