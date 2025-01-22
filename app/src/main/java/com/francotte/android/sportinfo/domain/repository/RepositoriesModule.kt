package com.francotte.android.sportinfo.domain.repository


import com.francotte.android.sportinfo.data.remote.api.SportInfoApi
import com.francotte.android.sportinfo.domain.repository.MatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Singleton
    @Provides
    fun providesMatchRepository(sportInfoApi: SportInfoApi) : MatchRepository = MatchRepository(sportInfoApi)


}