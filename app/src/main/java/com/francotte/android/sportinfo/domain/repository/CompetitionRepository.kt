package com.francotte.android.sportinfo.domain.repository

import com.francotte.android.sportinfo.data.remote.api.SportInfoApi
import com.francotte.android.sportinfo.data.remote.dto.competitions.asExternalModel
import com.francotte.android.sportinfo.domain.model.Competition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompetitionRepository @Inject constructor(
    private val sportInfoApi: SportInfoApi,

    ) {

    fun getCompetitionsList(): Flow<List<Competition>> {
        return flow {
            val remoteCompetitionsList = try {
                sportInfoApi.getCompetitions().competitions
            } catch (e: Exception) {
                null
            }
            remoteCompetitionsList?.let { remoteList ->
                emit(remoteList.map { it.asExternalModel() }) }
        }.flowOn(Dispatchers.IO)
    }
}

