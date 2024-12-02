package com.example.sportinfo.data.remote.dto.matches

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
data class HalfTime(
    @ColumnInfo("halfTimeAway") val away: Int,
    @ColumnInfo("halfTimeHome") val home: Int
)