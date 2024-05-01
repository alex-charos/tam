package gr.charos.tam.service

import gr.charos.tam.domain.Report
import gr.charos.tam.domain.Resource
import java.time.DayOfWeek
import java.time.LocalDate

class ResourceCalculator(r: Resource, h : List<LocalDate>) {
    val resource  = r
    val holidays = h

    fun calculateReport(to: LocalDate): Report {
        return  calculateReport(resource.dateStarted, to)
    }

    fun calculateReport(from: LocalDate, to: LocalDate): Report {
        val workingDays = calculateWorkingDays(from,to)
        val remainingDays = daysRemaining(to)
        val provisionalEnd = endOfContract()
        val leaves = leavesBetween(from,to)

        return Report( resource.name,remainingDays, provisionalEnd)
    }

    fun calculateWorkingDays(dateUntil: LocalDate): Int {
        return calculateWorkingDays(resource.dateStarted, dateUntil)
    }

    fun leavesBetween(from: LocalDate, to: LocalDate): List<LocalDate> {
        return resource.leaves.filter { date ->    (date.isAfter(from) || date.isEqual(from) ) &&
                                            (date.isBefore(to) || date.isEqual(to) )
        }
    }

    fun calculateWorkingDays(dateFrom: LocalDate, dateUntil: LocalDate): Int {
        if (dateUntil.isBefore(dateFrom)) {
            throw IllegalArgumentException("date start date must be before dateUntil");
        }
        var dateReading = dateFrom
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

    fun daysRemaining(asOfDate : LocalDate) : Int {
        val daysSpent = calculateWorkingDays(asOfDate)
        var daysRemaining = resource.totalDays - daysSpent
        return daysRemaining
    }

    fun endOfContract() : LocalDate {
        var daysRemaining =resource.totalDays
        var dateFinished = resource.dateStarted;

        while (daysRemaining != 0) {
            if (isWorkingDay(dateFinished)) {
                daysRemaining -= 1
            }
            dateFinished = dateFinished.plusDays(1)

        }
        return dateFinished
    }




    private fun isWorkingDay(date : LocalDate): Boolean {
        return !DayOfWeek.SATURDAY.equals( date.dayOfWeek)
                && !DayOfWeek.SUNDAY.equals(date.dayOfWeek)
                && !holidays.contains(date)
                && !resource.leaves.contains(date)
    }
}