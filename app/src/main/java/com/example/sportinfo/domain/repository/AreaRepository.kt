package com.example.sportinfo.domain.repository

import com.example.sportinfo.data.database.SportDatabase
import com.example.sportinfo.data.database.model.asExternalModel
import com.example.sportinfo.data.remote.api.AreaApi
import com.example.sportinfo.data.remote.dto.areas.asEntity
import com.example.sportinfo.domain.model.Area
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreaRepository @Inject constructor(
    val areaApi: AreaApi,
    val db: SportDatabase
) {

    private val dao = db.areaDao

    suspend fun getAreasList(): Flow<List<Area>> {
        val remoteAreasList = try {
            areaApi.getAreas().areas
        } catch (e: Exception) {
            null
        }

        remoteAreasList?.let { remoteList ->
            dao.clearAreasList()
            dao.insertAreasList(
                remoteList.map { it.asEntity() }
            )
        }
        return flow {
            emit(dao.searchAreasList().map { it.asExternalModel() })
        }

    }
}