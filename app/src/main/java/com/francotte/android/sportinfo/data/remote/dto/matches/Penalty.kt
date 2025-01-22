package com.francotte.android.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable

@Serializable
data class Penalty(
    val player: Player,
    val score: LightScore,
    val scored: Boolean,
    val team: Team
)