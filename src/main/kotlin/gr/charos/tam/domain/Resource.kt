package gr.charos.tam.domain

import java.time.DayOfWeek
import java.time.LocalDate

data class Resource(val id:String,
                    val name:String,
                    val company:String,
                    val dateStarted: LocalDate,
                    val totalDays:Int,
                    val holidays: List<LocalDate>) {
    var leaves = mutableListOf<LocalDate>()

    fun calculateWorkingDays(dateUntil: LocalDate): Int {
        return calculateWorkingDays(dateStarted, dateUntil)
    }

    fun calculateWorkingDays(dateFrom: LocalDate, dateUntil: LocalDate): Int {
        if (dateUntil.isBefore(dateFrom)) {
            throw IllegalArgumentException("date start date must be before dateUntil");
        }
        var dateReading = dateStarted
        var daysWorked = 0
        while (!dateReading.isAfter(dateUntil)) {
            if (isWorkingDay(dateReading)
            ) {
                daysWorked ++
            }

            dateReading = dateReading.plusDays(1)
        }

        return daysWorked

    }

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


    fun estimateEndOfContract() : LocalDate {
        return estimateEndOfContract(LocalDate.now())
    }

    fun estimateEndOfContract(asOfDate : LocalDate) : LocalDate {
        val daysSpent = calculateWorkingDays(asOfDate)
        var daysRemaining = totalDays - daysSpent
        if (daysRemaining == 0) {
            return asOfDate;
        }


        var step  =  1
        if (daysRemaining < 0) {
            step = -1
        }

        var dateFinished = asOfDate;
        while (daysRemaining != 0) {
            dateFinished = dateFinished.plusDays(step.toLong())
            daysRemaining -= step
        }
        return dateFinished
    }



    private fun isWorkingDay(date : LocalDate): Boolean {
        return !DayOfWeek.SATURDAY.equals( date.dayOfWeek)
                && !DayOfWeek.SUNDAY.equals(date.dayOfWeek)
                && !holidays.contains(date)
                && !leaves.contains(date)
    }

}
