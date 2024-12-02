package com.example.sportinfo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportinfo.data.database.model.CompetitionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompetitionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitionsList(competitionsList: List<CompetitionEntity>)

    @Query("DELETE FROM competitions")
    suspend fun clearCompetitionsList()

    @Query("SELECT * FROM competitions")
    suspend fun searchCompetitionsList() : List<CompetitionEntity>
}