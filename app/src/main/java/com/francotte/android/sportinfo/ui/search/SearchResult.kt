package com.francotte.android.sportinfo.ui.search

import com.francotte.android.sportinfo.domain.model.Competition
import com.francotte.android.sportinfo.domain.model.Team

/** An entity that holds the search result */
data class SearchResult(
    val teams: List<Team> = emptyList(),
    val competitions: List<Competition> = emptyList(),
)
