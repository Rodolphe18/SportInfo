package com.example.sportinfo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CurrentSeason(
    val currentMatchday: Int,
    val endDate: String? = "",
    val currentSeasonId: Int,
    val startDate: String? = "",
    val winner: Winner?
):Parcelable
