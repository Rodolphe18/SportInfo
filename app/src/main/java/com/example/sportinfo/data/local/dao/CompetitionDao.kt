package com.example.sportinfo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportinfo.data.local.model.CompetitionEntity

@Dao
interface CompetitionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitionsList(competitionsList: List<CompetitionEntity>)

    @Query("DELETE FROM competitions")
    suspend fun clearCompetitionsList()

    @Query("SELECT * FROM competitions")
    suspend fun searchCompetitionsList() : List<CompetitionEntity>
}