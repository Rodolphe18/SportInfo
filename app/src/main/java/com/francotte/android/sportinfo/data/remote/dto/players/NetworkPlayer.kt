package com.francotte.android.sportinfo.data.remote.dto.players

import com.francotte.android.sportinfo.data.remote.dto.players.NetworkCurrentTeam

data class NetworkPlayer(
    val networkCurrentTeam: NetworkCurrentTeam?,
    val dateOfBirth: String? = "",
    val firstName: String? = "",
    val id: Int,
    val lastName: String? = "",
    val lastUpdated: String? = "",
    val name: String? = "",
    val nationality: String?= "",
    val position: String? = "",
    val shirtNumber: Int
)