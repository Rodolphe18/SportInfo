package com.francotte.android.sportinfo.data.remote.dto.players

import com.francotte.android.sportinfo.data.remote.dto.areas.NetworkArea
import com.francotte.android.sportinfo.data.remote.dto.teams.NetworkContract
import com.francotte.android.sportinfo.data.remote.dto.teams.NetworkRunningCompetition

data class NetworkCurrentTeam(
    val address: String? = "",
    val area: NetworkArea?,
    val clubColors: String? = "",
    val contract: NetworkContract?,
    val crest: String?,
    val founded: Int,
    val id: Int,
    val name: String?,
    val runningCompetitions: List<NetworkRunningCompetition> = emptyList(),
    val shortName: String? = "",
    val tla: String? = "",
    val venue: String? = "",
    val website: String? = ""
)