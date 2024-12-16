package com.example.sportinfo.ui.search

import com.example.sportinfo.domain.model.Competition
import com.example.sportinfo.domain.model.Team

/** An entity that holds the search result */
data class SearchResult(
    val teams: List<Team> = emptyList(),
    val competitions: List<Competition> = emptyList(),
)
