package com.example.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable

@Serializable
data class FullTime(
    val away: Int,
    val home: Int
)