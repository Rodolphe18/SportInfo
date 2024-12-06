package com.example.sportinfo.data.remote.dto.teams

import com.example.sportinfo.domain.model.Squad
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSquad(
    val contract: NetworkContract?,
    val dateOfBirth: String? = "",
    val firstName: String? = "",
    val id: Int,
    val lastName: String? = "",
    val marketValue: Int = 0,
    val name: String? = "",
    val nationality: String? = "",
    val position: String? = "",
    val shirtNumber: Int = 0
)


fun NetworkSquad.asExternalModel() = Squad(contract?.asExternalModel(), dateOfBirth, firstName, id, lastName, marketValue, name, nationality, position, shirtNumber)