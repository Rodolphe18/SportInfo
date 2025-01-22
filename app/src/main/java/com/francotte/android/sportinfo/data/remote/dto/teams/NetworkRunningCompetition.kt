package com.francotte.android.sportinfo.data.remote.dto.teams

import com.francotte.android.sportinfo.domain.model.RunningCompetition
import kotlinx.serialization.Serializable

@Serializable
data class NetworkRunningCompetition(
    val code: String? = "",
    val emblem: String? = "",
    val id: Int,
    val name: String? = "",
    val type: String? = ""
)

fun NetworkRunningCompetition.asExternalModel() = RunningCompetition(code, emblem, id, name, type)