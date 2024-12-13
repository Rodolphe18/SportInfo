package com.example.sportinfo.data.remote.api

import com.example.sportinfo.data.remote.dto.competitions.NetworkCompetition
import com.example.sportinfo.data.remote.dto.competitions.NetworkCompetitions
import com.example.sportinfo.data.remote.dto.matches.Matches
import com.example.sportinfo.data.remote.dto.teams.NetworkTeams
import com.example.sportinfo.data.remote.dto.teams.NetworkTeamsByCompetition
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SportInfoApi {

    @GET("competitions")
    suspend fun getCompetitions() : NetworkCompetitions

    @GET("competitions/{competitionId}/matches")
    suspend fun getCompetitionMatches(@Path("competitionId") competitionId : String, @Query("matchday") status: Int) : Matches

    @GET("matches")
    suspend fun getTodayMatches() : Matches

    @GET("teams")
    suspend fun getTeams(@Query("limit") limit: Int = 20, @Query("offset") offset: Int) : NetworkTeams

    @GET("competitions/PL/teams")
    suspend fun getPremierLeagueTeams() : NetworkTeamsByCompetition

    @GET("competitions/PD/teams")
    suspend fun getLigaTeams() : NetworkTeamsByCompetition

    @GET("competitions/FL1/teams")
    suspend fun getLigue1Teams() : NetworkTeamsByCompetition

    @GET("competitions/PPL/teams")
    suspend fun getPrimeiraLigue() : NetworkTeamsByCompetition

    @GET("competitions/SA/teams")
    suspend fun getSerieATeams() : NetworkTeamsByCompetition

    @GET("competitions/BL1/teams")
    suspend fun getBundesligaTeams() : NetworkTeamsByCompetition


}