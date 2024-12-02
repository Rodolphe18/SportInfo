package com.example.sportinfo.data.remote.dto.competitions

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSeason(
    val currentMatchday: Int,
    val endDate: String,
    @ColumnInfo("seasonId") @PrimaryKey val id: Int,
    val startDate: String,
    @Ignore @Embedded val winner: NetworkWinner? = null
) {
    constructor(currentMatchday: Int, endDate: String, id : Int, startDate: String) : this(currentMatchday, endDate, id, startDate, null)
}
