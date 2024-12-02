package com.example.sportinfo.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportinfo.data.remote.dto.areas.NetworkArea
import com.example.sportinfo.data.remote.dto.competitions.NetworkCurrentSeason
import com.example.sportinfo.data.remote.dto.areas.asExternalModel
import com.example.sportinfo.data.remote.dto.competitions.asExternalModel
import com.example.sportinfo.domain.model.Competition

@Entity(tableName = "competitions")
data class CompetitionEntity(
    @Embedded val area: NetworkArea,
    val competitionCode: String,
    @Embedded val currentSeason: NetworkCurrentSeason,
    val emblem: String,
    @PrimaryKey val id: Int?,
    val competitionLastUpdated: String,
    val competitionName: String,
    val type: String
)

fun CompetitionEntity.asExternalModel() : Competition {
    return Competition(area.asExternalModel(), competitionCode, currentSeason.asExternalModel(), emblem, id, competitionLastUpdated, competitionName, type)
}



