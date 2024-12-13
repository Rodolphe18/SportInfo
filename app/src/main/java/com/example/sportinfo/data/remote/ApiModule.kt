package com.example.sportinfo.data.remote

import com.example.sportinfo.data.remote.api.HttpLoggingInterceptor
import com.example.sportinfo.data.remote.api.MyInterceptor
import com.example.sportinfo.data.remote.api.SportInfoApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
        addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
    }.build()


    @Singleton
    @Provides
    fun providesApi() : SportInfoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.football-data.org/v4/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SportInfoApi::class.java)
    }


}
