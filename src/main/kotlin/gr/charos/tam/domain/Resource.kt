package gr.charos.tam.domain

import java.time.DayOfWeek
import java.time.LocalDate

data class Resource(val id:String,
                    val name:String,
                    val company:String,
                    val dateStarted: LocalDate,
                    var totalDays:Int) {
    var leaves = mutableListOf<LocalDate>()


    fun addLeave(dateFrom: LocalDate) {
        return addLeave(dateFrom, dateFrom)
    }

    fun addLeave(dateFrom: LocalDate, dateTo: LocalDate) {
        if (dateTo.isBefore(dateFrom)) {
            throw IllegalArgumentException("date to be before dateUntil");
        }
        var dateReading = dateFrom;
        while (!dateReading.isAfter(dateTo)) {
            leaves.add(dateReading)
            dateReading = dateReading.plusDays(1)
        }


    }

    fun addExtension(days : Int) {
        totalDays +=days
    }







}
