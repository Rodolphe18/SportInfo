package com.example.sportinfo.domain.model

data class Team(
    val address: String? = "",
    val area: Area?,
    val clubColors: String? = "",
    val coach: Coach?,
    val crest: String? = "",
    val founded: Int,
    val id: Int,
    val lastUpdated: String? = "",
    val marketValue: Int,
    val name: String? = "",
    val runningCompetitions: List<RunningCompetition?> = emptyList(),
    val shortName: String? = "",
    val squad: List<Squad?> = emptyList(),
    val tla: String? = "",
    val venue: String? = "",
    val website: String? = "",
    val isFavorite: Boolean = false
)