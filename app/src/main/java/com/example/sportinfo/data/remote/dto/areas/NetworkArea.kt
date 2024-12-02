package com.example.sportinfo.data.remote.dto.areas

import com.example.sportinfo.data.database.model.AreaEntity
import com.example.sportinfo.domain.model.Area
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArea(
   @SerializedName("id") val areaId: Int?,
   @SerializedName("name") val areaName : String?,
   val countryCode: String?,
   val flag: String?,
   val parentAreaId: Int?,
   val parentArea: String?
)

fun NetworkArea.asEntity() = AreaEntity(areaId ?: 0, areaName ?: "", countryCode ?: "", flag ?: "", parentAreaId ?: 0, parentArea ?: "")

fun NetworkArea.asExternalModel() = Area(areaId ?: 0, areaName ?: "", countryCode ?: "", flag ?: "", parentAreaId ?: 0, parentArea ?: "")
