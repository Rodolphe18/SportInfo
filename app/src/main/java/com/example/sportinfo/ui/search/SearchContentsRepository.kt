package com.example.sportinfo.ui.search

import kotlinx.coroutines.flow.Flow

interface SearchContentsRepository {

    fun searchContents(searchQuery: String): Flow<SearchResult>

}