package com.example.sportinfo.domain.model

import com.example.sportinfo.data.remote.dto.teams.NetworkContract

data class Squad(
    val contract: Contract?,
    val dateOfBirth: String? = "",
    val firstName: String? = "",
    val id: Int,
    val lastName: String? = "",
    val marketValue: Int,
    val name: String? = "",
    val nationality: String? = "",
    val position: String? = "",
    val shirtNumber: Int
)