package com.francotte.android.sportinfo.data.remote.dto.competitions

import com.francotte.android.sportinfo.domain.model.Winner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("winner")
@Serializable
data class NetworkWinner(
    val address: String? = "",
    val clubColors: String? = "",
    val crest: String? = "",
    val founded: Int? = 0,
    val id: Int? = 0,
    val lastUpdated: String? = "",
    val name: String? = "",
    val shortName: String? = "",
    val tla: String? = "",
    val venue: String? = "",
    val website: String? = ""
)

fun NetworkWinner.asExternalModel() = Winner(address.orEmpty(), clubColors.orEmpty(), crest.orEmpty(), founded ?: 0, id ?: 0, lastUpdated.orEmpty(), name.orEmpty(), shortName.orEmpty(), tla.orEmpty(), venue.orEmpty(), website.orEmpty())
