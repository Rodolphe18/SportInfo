package com.example.sportinfo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Winner(
    val address: String?,
    val clubColors: String?,
    val crest: String?,
    val founded: Int,
    val winnerId: Int,
    val lastUpdated: String?,
    val name: String?,
    val shortName: String?,
    val tla: String?,
    val venue: String?,
    val website: String?
):Parcelable

