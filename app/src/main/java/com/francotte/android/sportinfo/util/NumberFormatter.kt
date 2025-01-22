package com.francotte.android.sportinfo.util

object NumberFormatter {

    fun getFormattedDay(value:Int):String {
        return when (value) {
            1 -> "${value}st matchweek"
            2 -> "${value}nd matchweek"
            3 -> "${value}rd matchweek"
            else -> "${value}th matchweek"
        }

    }

}