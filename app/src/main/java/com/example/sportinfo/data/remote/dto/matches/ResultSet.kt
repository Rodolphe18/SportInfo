package com.example.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable

@Serializable
data class ResultSet(
    val count: Int,
    val first: String,
    val last: String,
    val played: Int
)