package com.example.sportinfo.data.remote.dto.matches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Score(
    val duration: String? = null,
    val fullTime: FullTime,
    val halfTime: HalfTime,
    val winner: String?
)

@SerialName("score")
@Serializable
data class LightScore(
    val away: Int,
    val home: Int
)

