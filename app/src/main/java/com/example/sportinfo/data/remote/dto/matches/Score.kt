package com.example.sportinfo.data.remote.dto.matches

import androidx.room.Embedded
import com.example.sportinfo.data.remote.dto.matches.FullTime
import com.example.sportinfo.data.remote.dto.matches.HalfTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Score(
    val duration: String,
    @Embedded val fullTime: FullTime,
    @Embedded val halfTime: HalfTime,
    val winner: String?
)

@SerialName("score")
@Serializable
data class LightScore(
    val away: Int,
    val home: Int
)

