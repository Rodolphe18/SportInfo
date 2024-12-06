package com.example.sportinfo.data.remote.dto.teams

import com.example.sportinfo.domain.model.Contract
import kotlinx.serialization.Serializable

@Serializable
data class NetworkContract(
    val start: String? = "",
    val until: String? = ""
)

fun NetworkContract.asExternalModel() = Contract(start, until)