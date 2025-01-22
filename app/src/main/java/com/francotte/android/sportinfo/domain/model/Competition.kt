package com.francotte.android.sportinfo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Competition(
    val area: Area?,
    val code: String?,
    val currentSeason: CurrentSeason?,
    val emblem: String?,
    val id : Int?,
    val lastUpdated: String?,
    val name: String?,
    val seasons: List<Season>? = emptyList(),
    val type: String?,
    val isFavorite: Boolean = false,
): Parcelable

