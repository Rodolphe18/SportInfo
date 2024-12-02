package com.example.sportinfo.data.remote.api

import com.example.sportinfo.data.remote.dto.competitions.NetworkCompetition
import com.example.sportinfo.data.remote.dto.competitions.NetworkCompetitions
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionApi {

    @GET("competitions")
    suspend fun getCompetitions() : NetworkCompetitions

    @GET("competitions/{competitionId}")
    suspend fun getCompetition(@Path("competitionId") competitionId : String) : NetworkCompetition


}