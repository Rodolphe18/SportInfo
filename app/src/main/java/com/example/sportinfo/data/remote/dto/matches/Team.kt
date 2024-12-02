package com.example.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: Int,
    val name: String
)