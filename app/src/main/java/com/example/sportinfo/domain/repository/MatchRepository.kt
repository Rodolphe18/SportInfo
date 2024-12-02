package com.example.sportinfo.domain.repository

import com.example.sportinfo.data.database.SportDatabase
import com.example.sportinfo.data.remote.api.MatchApi
import com.example.sportinfo.data.remote.dto.matches.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MatchRepository @Inject constructor(val matchApi : MatchApi, val db: SportDatabase) {

    val dao = db.matchDao

    suspend fun getRemoteMatchList(): Flow<List<Match>> {
        return flow {
            val competitions = matchApi.getPremierLeagueMatches().matches
            emit(competitions)
        }
    }

    suspend fun getLocalMatchesList(): Flow<List<Match>> {
        val remoteMatchesList = try {
            matchApi.getPremierLeagueMatches().matches
        } catch (e: Exception) {
            null
        }
        remoteMatchesList?.let {
            dao.clearMatchesList()
            dao.insertMatchesList(remoteMatchesList)
        }
        return dao.searchMatchesList()
    }
}
