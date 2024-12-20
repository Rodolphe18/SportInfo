package com.example.sportinfo.data.remote.dto.matches

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class AwayTeam(
    @Transient val bench: List<Unit> = emptyList(),
    val coach: Coach?,
    val crest: String,
    val formation: String?,
    val id: Int,
    val leagueRank: Int,
    @Transient val lineup: List<Unit> = emptyList(),
    val name: String,
    val shortName: String,
    val tla: String
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AwayTeam

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        return result
    }


}