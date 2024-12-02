package com.example.sportinfo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportinfo.data.local.dao.AreaDao
import com.example.sportinfo.data.local.dao.CompetitionDao
import com.example.sportinfo.data.local.dao.MatchDao
import com.example.sportinfo.data.local.model.AreaEntity
import com.example.sportinfo.data.local.model.CompetitionEntity
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