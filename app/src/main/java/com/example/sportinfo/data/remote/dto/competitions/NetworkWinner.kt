package com.example.sportinfo.data.remote.dto.competitions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportinfo.domain.model.Winner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@SerialName("winner")
@Serializable
data class NetworkWinner(
    @ColumnInfo("winnerAddress") val address: String?,
    @ColumnInfo("winnerClubColors") val clubColors: String?,
    @ColumnInfo("winnerCrest") val crest: String?,
    @ColumnInfo("winnerFounded") val founded: Int?,
    @PrimaryKey @ColumnInfo("winnerId") val id: Int?,
    @ColumnInfo("winnerLastUpdated") val lastUpdated: String?,
    @ColumnInfo("winnerName") val name: String?,
    @ColumnInfo("winnerShortName") val shortName: String?,
    @ColumnInfo("winnerTla") val tla: String?,
    @ColumnInfo("winnerVenue") val venue: String?,
    @ColumnInfo("winnerWebSite") val website: String?
)

fun NetworkWinner.asExternalModel() = Winner(address ?: "", clubColors ?: "", crest ?: "", founded ?: -1, id ?: -1, lastUpdated ?: "", name ?: "", shortName ?: "", tla ?: "", venue ?: "", website ?: "")
