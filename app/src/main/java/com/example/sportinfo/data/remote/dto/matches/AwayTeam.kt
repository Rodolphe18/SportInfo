package com.example.sportinfo.data.remote.dto.matches

import androidx.room.ColumnInfo
import androidx.room.Ignore
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class AwayTeam(
    @Ignore @Transient val bench: List<Unit> = emptyList(),
    @Ignore val coach: Coach?,
    @ColumnInfo("awayTeamCrest") val crest: String,
    @ColumnInfo("awayTeamFormation") val formation: String?,
    @ColumnInfo("awayTeamId") val id: Int,
    @ColumnInfo("awayTeamLeagueRank") val leagueRank: Int,
    @Ignore @Transient val lineup: List<Unit> = emptyList(),
    @ColumnInfo("awayTeamName") val name: String,
    @ColumnInfo("awayTeamShortName") val shortName: String,
    @ColumnInfo("awayTeamTla") val tla: String
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