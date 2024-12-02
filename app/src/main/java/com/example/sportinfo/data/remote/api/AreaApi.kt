package com.example.sportinfo.data.remote.api

import com.example.sportinfo.data.remote.dto.areas.NetworkAreas
import com.example.sportinfo.data.remote.dto.areas.NetworkArea
import retrofit2.http.GET
import retrofit2.http.Path

interface AreaApi {

    @GET("areas")
    suspend fun getAreas() : NetworkAreas

    @GET("areas/{id}")
    suspend fun getArea(@Path("id") areaId : String) : NetworkArea


}