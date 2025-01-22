package com.francotte.android.sportinfo.data.remote.dto.teams

import com.francotte.android.sportinfo.domain.model.Contract
import kotlinx.serialization.Serializable

@Serializable
data class NetworkContract(
    val start: String? = "",
    val until: String? = ""
)

fun NetworkContract.asExternalModel() = Contract(start, until)