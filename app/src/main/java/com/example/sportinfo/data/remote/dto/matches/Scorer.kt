package com.example.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable

@Serializable
data class Scorer(
    val id: Int,
    val name: String? = null
)