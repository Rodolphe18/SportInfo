package com.example.sportinfo.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportinfo.domain.model.Area

@Entity(tableName = "areas")
data class AreaEntity(
    @PrimaryKey @ColumnInfo("area_id") val id: Int?,
    @ColumnInfo("area_name") val name : String?,
    @ColumnInfo("area_countryCode") val countryCode: String?,
    @ColumnInfo("area_flag") val flag: String?,
    @ColumnInfo("area_parentAreaId") val parentAreaId: Int?,
    @ColumnInfo("area_parentArea") val parentArea: String?
)

fun AreaEntity.asExternalModel() = Area(id, name, countryCode, flag, parentAreaId, parentArea)