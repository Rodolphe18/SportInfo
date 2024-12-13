package com.example.sportinfo.util


import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import java.time.OffsetDateTime

object DateTimeFormatter {

    fun getFormattedDate(value:String):String {
     val offSet = OffsetDateTime.parse(value)
        val dayOfWeek = offSet.dayOfWeek.name.lowercase().capitalize(Locale.current)
        val month = offSet.month.name.lowercase()
        val dayOfMonth = offSet.dayOfMonth
        val formattedDate = "$dayOfWeek $dayOfMonth $month "
        return formattedDate
    }

    fun getFormattedTime(value:String):String {
        val offSet = OffsetDateTime.parse(value)
        val time = "${offSet.hour}h${if (offSet.minute != 0) offSet.minute else "00"}"
        return time
    }

}