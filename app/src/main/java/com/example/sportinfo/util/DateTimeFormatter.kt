package com.example.sportinfo.util


import java.time.OffsetDateTime

object DateTimeFormatter {

    fun getFormattedDate(value:String):String {
     val offSet = OffsetDateTime.parse(value)
        val dayOfWeek = offSet.dayOfWeek.name.lowercase()
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