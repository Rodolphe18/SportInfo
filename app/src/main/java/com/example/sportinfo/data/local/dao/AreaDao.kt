package com.example.sportinfo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportinfo.data.local.model.AreaEntity

@Dao
interface AreaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreasList(areasList: List<AreaEntity>)

    @Query("DELETE FROM areas")
    suspend fun clearAreasList()

    @Query("SELECT * FROM areas")
    suspend fun searchAreasList() : List<AreaEntity>

}