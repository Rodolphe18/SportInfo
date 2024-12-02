package com.example.sportinfo.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

enum class HomeChipType(val title: String, var isSelected: MutableState<Boolean>, val areaId:Int) {
    EUROPE("EUROPE", mutableStateOf(false), 2077), SOUTH_AMERICA("SOUTH AMERICA", mutableStateOf(false), 2220), NORTH_AMERICA("N/C AMERICA", mutableStateOf(false), 2159), AFRICA("AFRICA", mutableStateOf(false), 2001), ASIA("ASIA", mutableStateOf(false), 2014), OTHER("World", mutableStateOf(false), 2267)
}



