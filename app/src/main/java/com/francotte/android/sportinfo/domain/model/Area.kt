package com.francotte.android.sportinfo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Area(
    val id: Int?,
    val name : String?,
    val countryCode: String?,
    val flag: String?,
    val parentAreaId: Int?,
    val parentArea: String?
):Parcelable




