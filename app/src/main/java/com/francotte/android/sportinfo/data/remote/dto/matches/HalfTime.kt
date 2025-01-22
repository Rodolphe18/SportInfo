package com.francotte.android.sportinfo.data.remote.dto.matches


import kotlinx.serialization.Serializable

@Serializable
data class HalfTime(
    val away: Int,
    val home: Int
)