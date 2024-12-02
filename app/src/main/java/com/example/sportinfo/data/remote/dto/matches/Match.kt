package com.example.sportinfo.data.remote.dto.matches

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.sportinfo.data.remote.dto.areas.NetworkArea
import com.example.sportinfo.data.remote.dto.competitions.NetworkLightCompetition
import com.example.sportinfo.data.remote.dto.competitions.NetworkSeason
import kotlinx.serialization.Serializable

@Entity(tableName = "matches_entity")
@Serializable
data class Match(
    @Embedded val area: NetworkArea,
    val attendance: Int,
    @Embedded val awayTeam: AwayTeam,
    @Embedded val competition: NetworkLightCompetition,
    @Ignore val goals: List<Goal>? = emptyList(),
    @Embedded val homeTeam: HomeTeam,
    @PrimaryKey val id: Int,
    val injuryTime: Int,
    val lastUpdated: String,
    val matchday: Int,
    val minute: String?,
    @Embedded val odds: Odds,
    @Ignore val penalties: List<Penalty> = emptyList(),
    @Ignore val referees: List<Referee> = emptyList(),
    @Embedded val score: Score,
    @Embedded val season: NetworkSeason,
    val stage: String,
    val status: String,
    val utcDate: String,
    val venue: String?
) {
    constructor(
        area: NetworkArea,
        attendance: Int,
        awayTeam: AwayTeam,
        competition: NetworkLightCompetition,
        homeTeam: HomeTeam,
        id: Int,
        injuryTime: Int,
        lastUpdated: String,
        matchday: Int,
        minute: String?,
        odds: Odds,
        score: Score,
        season: NetworkSeason,
        stage: String,
        status: String,
        utcDate: String,
        venue: String?
    ) : this(
        area, attendance, awayTeam, competition, emptyList(),
        homeTeam,
        id,
        injuryTime,
        lastUpdated,
        matchday,
        minute,
        odds,
        emptyList(),
        emptyList(),
        score,
        season,
        stage,
        status,
        utcDate,
        venue
    )

}