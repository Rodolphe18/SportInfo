package com.francotte.android.sportinfo.domain.model

import com.francotte.android.sportinfo.domain.model.Contract

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