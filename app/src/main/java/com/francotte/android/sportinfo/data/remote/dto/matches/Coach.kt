package com.francotte.android.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable

@Serializable
data class Coach(
    val id: Int,
    val name: String? = "",
    val nationality: String? = ""
)