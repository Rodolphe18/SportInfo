package com.example.sportinfo.data.remote.dto.areas

import kotlinx.serialization.Serializable


@Serializable
data class NetworkAreas(
    val areas: List<NetworkArea>,
    val count: Int,
    val filters: Filters
)

@Serializable
class Filters