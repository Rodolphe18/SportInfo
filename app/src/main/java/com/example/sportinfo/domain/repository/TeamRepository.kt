package com.example.sportinfo.domain.repository

import android.util.Log
import com.example.sportinfo.data.remote.api.SportInfoApi
import com.example.sportinfo.data.remote.dto.teams.asExternalModel
import com.example.sportinfo.domain.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepository @Inject constructor(private val sportInfoApi: SportInfoApi) {


    fun getTeamsList(): Flow<List<Team>> {
        return flow {
            val remoteTeamsList = try {
                val result =  sportInfoApi.getTeams()
                Log.d("debug_", result.toString())
                result
            } catch (e: Exception) {
                null
            }
            remoteTeamsList?.let { remoteList ->
                emit(remoteList.teams.map { it.asExternalModel() }) }
        }.flowOn(Dispatchers.IO)
    }

    fun getPremierLeagueTeams(): Flow<List<Team>> {
        return flow {
            val remoteTeamsList = try {
                sportInfoApi.getPremierLeagueTeams()
            } catch (e: Exception) {
                null
            }
            remoteTeamsList?.let { remoteList ->
                emit(remoteList.teams.map { it.asExternalModel() }) }
        }.flowOn(Dispatchers.IO)
    }

    fun getLigue1Teams(): Flow<List<Team>> {
        return flow {
            val remoteTeamsList = try {
                sportInfoApi.getLigue1Teams()
            } catch (e: Exception) {
                null
            }
            remoteTeamsList?.let { remoteList ->
                emit(remoteList.teams.map { it.asExternalModel() }) }
        }.flowOn(Dispatchers.IO)
    }

    fun getSerieATeams(): Flow<List<Team>> {
        return flow {
            val remoteTeamsList = try {
                sportInfoApi.getSerieATeams()
            } catch (e: Exception) {
                null
            }
            remoteTeamsList?.let { remoteList ->
                emit(remoteList.teams.map { it.asExternalModel() }) }
        }.flowOn(Dispatchers.IO)
    }

    fun getLigaTeams(): Flow<List<Team>> {
        return flow {
            val remoteTeamsList = try {
                sportInfoApi.getLigaTeams()
            } catch (e: Exception) {
                null
            }
            remoteTeamsList?.let { remoteList ->
                emit(remoteList.teams.map { it.asExternalModel() }) }
        }.flowOn(Dispatchers.IO)
    }

    fun getBundesligaTeams(): Flow<List<Team>> {
        return flow {
            val remoteTeamsList = try {
                sportInfoApi.getBundesligaTeams()
            } catch (e: Exception) {
                null
            }
            remoteTeamsList?.let { remoteList ->
                emit(remoteList.teams.map { it.asExternalModel() }) }
        }.flowOn(Dispatchers.IO)
    }

    fun getPrimeiraDivisionTeams(): Flow<List<Team>> {
        return flow {
            val remoteTeamsList = try {
                sportInfoApi.getPrimeiraLigue()
            } catch (e: Exception) {
                null
            }
            remoteTeamsList?.let { remoteList ->
                emit(remoteList.teams.map { it.asExternalModel() }) }
        }.flowOn(Dispatchers.IO)
    }


}