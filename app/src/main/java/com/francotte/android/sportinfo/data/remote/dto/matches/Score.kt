package com.francotte.android.sportinfo.data.remote.dto.matches


import com.francotte.android.sportinfo.data.remote.dto.matches.FullTime
import com.francotte.android.sportinfo.data.remote.dto.matches.HalfTime
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

