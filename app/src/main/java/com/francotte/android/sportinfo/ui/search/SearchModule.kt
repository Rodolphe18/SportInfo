package com.francotte.android.sportinfo.ui.search

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun bindSearchRepository(searchContentsImpl: SearchContentsImpl): SearchContentsRepository
}