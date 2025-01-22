package com.francotte.android.sportinfo.domain.repository


import com.francotte.android.sportinfo.data.remote.api.SportInfoApi
import com.francotte.android.sportinfo.data.remote.dto.matches.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MatchRepository @Inject constructor(val sportInfoApi: SportInfoApi) {


    fun getTodayMatchList(): Flow<List<Match>> {
        return flow {
            val matches =
                try {
                    sportInfoApi.getTodayMatches().matches
                } catch (e: Exception) {
                    null
                }
            matches?.let {
                emit(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCompetitionMatchList(competitionId: String, matchDay:Int): Flow<List<Match>?> {
        return flow {
            val matches =
                try {
                    sportInfoApi.getCompetitionMatches(competitionId, matchDay).matches
                } catch (e: Exception) {
                    null
                }
            matches?.let {
                emit(it)
            }
        }.flowOn(Dispatchers.IO)
    }

}
