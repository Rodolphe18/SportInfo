package com.francotte.android.sportinfo.ui.search

import com.francotte.android.sportinfo.data.remote.api.SportInfoApi
import com.francotte.android.sportinfo.data.remote.dto.competitions.asExternalModel
import com.francotte.android.sportinfo.data.remote.dto.teams.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SearchContentsImpl @Inject constructor(private val sportInfoApi: SportInfoApi) :
    SearchContentsRepository {

    override fun searchContents(searchQuery: String): Flow<SearchResult> {
        val teams = flow { emit(sportInfoApi.getTeams(limit = 500, offset = 0).teams.map { it.asExternalModel() }) }
        val competitions = flow { emit(sportInfoApi.getCompetitions().competitions.map { it.asExternalModel() }) }
        val query = flow { emit(searchQuery) }
        return combine(teams, competitions, query) { teams, competitions, query ->
            SearchResult(
                teams = teams.filter { it.name?.contains(query, true) == true },
                competitions = competitions.filter { it.name?.contains(query, true) == true  },
            )
        }
    }

}