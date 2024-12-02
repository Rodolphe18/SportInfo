package com.example.sportinfo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportinfo.data.database.dao.AreaDao
import com.example.sportinfo.data.database.dao.CompetitionDao
import com.example.sportinfo.data.database.dao.MatchDao
import com.example.sportinfo.data.database.model.AreaEntity
import com.example.sportinfo.data.database.model.CompetitionEntity
import com.example.sportinfo.data.remote.dto.matches.Match

@Database(
    entities = [CompetitionEntity::class, Match::class, AreaEntity::class],
    version = 24,
    exportSchema = false
)
abstract class SportDatabase : RoomDatabase() {
    abstract val competitionDao : CompetitionDao
    abstract val matchDao : MatchDao
    abstract val areaDao : AreaDao

}