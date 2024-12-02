package com.example.sportinfo.data.remote.dto.matches

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
data class FullTime(
    @ColumnInfo("fullTimeAway") val away: Int,
    @ColumnInfo("fullTimeHome") val home: Int
)