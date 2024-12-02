package com.example.sportinfo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportinfo.data.remote.dto.matches.Match
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchesList(competitionsList: List<Match>)

    @Query("DELETE FROM matches_entity")
    suspend fun clearMatchesList()

    @Query("SELECT * FROM matches_entity")
    fun searchMatchesList() : Flow<List<Match>>

    @Query("SELECT awayTeamName, homeTeamName FROM matches_entity")
    fun fetchLightMatch() : Flow<List<MatchTeamsName>>


}

data class MatchTeamsName(val awayTeamName : String?, val homeTeamName : String?)