package com.example.sportinfo.domain.repository

import com.example.sportinfo.data.local.SportDatabase
import com.example.sportinfo.data.local.model.asExternalModel
import com.example.sportinfo.data.remote.api.CompetitionApi
import com.example.sportinfo.data.remote.dto.competitions.asEntity
import com.example.sportinfo.domain.model.Competition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompetitionRepository @Inject constructor(
    val competitionApi: CompetitionApi,
    val db: SportDatabase
) {

    private val dao = db.competitionDao

    suspend fun getCompetitionsList(): Flow<List<Competition>> {
        val remoteCompetitionsList = try {
            competitionApi.getCompetitions().competitions
        } catch (e: Exception) {
            null
        }
        remoteCompetitionsList?.let { remoteList ->
            dao.clearCompetitionsList()
            dao.insertCompetitionsList(
                remoteList.map { it.asEntity() }
            )
        }
        return flow {
            emit(dao.searchCompetitionsList().map { it.asExternalModel() }) }
    }
}

