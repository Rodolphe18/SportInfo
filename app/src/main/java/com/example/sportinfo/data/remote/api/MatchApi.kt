package com.example.sportinfo.data.remote.api

import com.example.sportinfo.data.remote.dto.matches.Matches
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchApi {

    @GET("competitions/{id}/matches")
    suspend fun getPremierLeagueMatches(@Path("id") id : String = "PL") : Matches
}