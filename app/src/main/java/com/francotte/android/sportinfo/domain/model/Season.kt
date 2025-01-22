package com.francotte.android.sportinfo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Season(
    val currentMatchday: Int,
    val endDate: String? = "",
    val id: Int,
    val startDate: String? = "",
    val winner: Winner? = null
):Parcelable