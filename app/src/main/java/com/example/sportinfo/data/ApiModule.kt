package com.example.sportinfo.data

import android.app.Application
import androidx.room.Room
import com.example.sportinfo.data.database.SportDatabase
import com.example.sportinfo.data.remote.api.AreaApi
import com.example.sportinfo.data.remote.api.CompetitionApi
import com.example.sportinfo.data.remote.api.MatchApi
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

    private val client = OkHttpClient.Builder().apply {addInterceptor(MyInterceptor()) }.build()

    @Singleton
    @Provides
    private inline fun <reified A> providesApi() : A {
        return Retrofit.Builder()
            .baseUrl("https://api.football-data.org/v4/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun providesCompetitionApi() : CompetitionApi = providesApi()

    @Singleton
    @Provides
    fun providesMatchApi() : MatchApi = providesApi()

    @Singleton
    @Provides
    fun providesAreaApi() : AreaApi = providesApi()

    @Singleton
    @Provides
    fun providesSportDatabase(app: Application) : SportDatabase {
        return Room.databaseBuilder(app, SportDatabase::class.java, "sport_databse.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}

inline fun <reified T> Retrofit.create() : T = create(T::class.java)