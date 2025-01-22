package com.francotte.android.sportinfo.ui.search

import kotlinx.coroutines.flow.Flow

interface SearchContentsRepository {

    fun searchContents(searchQuery: String): Flow<SearchResult>

}