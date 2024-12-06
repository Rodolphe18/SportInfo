package com.example.sportinfo.util


import java.time.OffsetDateTime

object DateTimeFormatter {

    fun getFormattedDate(value:String):String {
     val offSet = OffsetDateTime.parse(value)
        val year = offSet.year
        val month = if(offSet.month.name.lowercase() != "may") offSet.month.name.substring(0, 2) else "may"
        val dayOfMonth = offSet.dayOfMonth
        val formattedDate = "$year - $month. - $dayOfMonth"
        return formattedDate
    }

    fun getFormattedTime(value:String):String {
        val offSet = OffsetDateTime.parse(value)
        val time = "${offSet.hour}h${if (offSet.minute != 0) offSet.minute else "00"}"
        return time
    }

}