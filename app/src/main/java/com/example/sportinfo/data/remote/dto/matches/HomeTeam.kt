package com.example.sportinfo.data.remote.dto.matches

import androidx.room.ColumnInfo
import androidx.room.Ignore
import com.example.sportinfo.data.remote.dto.matches.Coach
import kotlinx.serialization.Serializable

@Serializable
data class HomeTeam(
    @Ignore @Transient val bench: List<Unit> = emptyList(),
    @Ignore val coach: Coach?,
    @ColumnInfo("homeTeamCrest") val crest: String,
    @ColumnInfo("homeTeamFormation") val formation: String?,
    @ColumnInfo("homeTeamId") val id: Int,
    @ColumnInfo("homeTeamLeagueRank") val leagueRank: Int,
    @Ignore @Transient val lineup: List<Unit> = emptyList(),
    @ColumnInfo("homeTeamName") val name: String,
    @ColumnInfo("homeTeamShortName") val shortName: String,
    @ColumnInfo("homeTeamTla") val tla: String
) {
    constructor(
        crest: String,
        formation: String?,
        id: Int,
        leagueRank: Int,
        name: String,
        shortName: String,
        tla: String
    ) : this(emptyList(), null, crest, formation, id, leagueRank, emptyList(), name, shortName, tla)
}