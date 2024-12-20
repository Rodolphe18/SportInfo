package com.example.sportinfo.data.remote.dto.matches

import com.example.sportinfo.data.remote.dto.areas.NetworkArea
import com.example.sportinfo.data.remote.dto.competitions.NetworkLightCompetition
import com.example.sportinfo.data.remote.dto.competitions.NetworkSeason
import kotlinx.serialization.Serializable


@Serializable
data class Match(
    val area: NetworkArea,
    val attendance: Int,
    val awayTeam: AwayTeam,
    val competition: NetworkLightCompetition,
    val goals: List<Goal>?,
    val homeTeam: HomeTeam,
    val id: Int,
    val injuryTime: Int,
    val lastUpdated: String? = "",
    val matchday: Int,
    val minute: Int = 0,
    val odds: Odds,
    val penalties: List<Penalty> = emptyList(),
    val referees: List<Referee> = emptyList(),
    val score: Score?,
    val season: NetworkSeason,
    val stage: String? = "",
    val status: String? = "",
    val utcDate: String? = "",
    val venue: String? = ""
) {
    constructor(
        area: NetworkArea,
        attendance: Int,
        awayTeam: AwayTeam,
        competition: NetworkLightCompetition,
        goals: List<Goal>,
        homeTeam: HomeTeam,
        id: Int,
        injuryTime: Int,
        lastUpdated: String,
        matchday: Int,
        minute: Int,
        odds: Odds,
        score: Score,
        season: NetworkSeason,
        stage: String,
        status: String,
        utcDate: String,
        venue: String?
    ) : this(
        area, attendance, awayTeam, competition, goals,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Match
        return id == other.id
    }

    override fun hashCode(): Int {
        return id * 31
    }


}

