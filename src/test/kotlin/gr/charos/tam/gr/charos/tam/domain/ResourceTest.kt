package gr.charos.tam.gr.charos.tam.domain

import gr.charos.tam.domain.Resource
import gr.charos.tam.domain.ResourceLoader
import gr.charos.tam.service.impl.OfficeHolidaysCalendarProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ResourceTest {




    @Test
    fun shouldCalculateWorkingDays() {
        val rc = ResourceLoader(OfficeHolidaysCalendarProvider())
        val start = LocalDate.of(2024,1,1)

        val resource = Resource("1", "alex","OPAP", start, 100,  rc.loadHolidays())

        val res =   resource.calculateWorkingDays(LocalDate.of(2024,1,31))
        assertEquals(22, res)


    }

    @Test
    fun shouldCalculateWorkingDaysWithSingleDayLeave() {
        val rc = ResourceLoader(OfficeHolidaysCalendarProvider())
        val start = LocalDate.of(2024,1,1)

        val resource = Resource("1", "alex","OPAP", start, 100,  rc.loadHolidays())
        resource.addLeave(LocalDate.of(2024,1,2), LocalDate.of(2024,1,2))

        val res =   resource.calculateWorkingDays(LocalDate.of(2024,1,31))
        assertEquals(21, res)
    }


    @Test
    fun shouldCalculateWorkingDaysWithMultipleDaysLeave() {
        val rc = ResourceLoader(OfficeHolidaysCalendarProvider())
        val start = LocalDate.of(2024,1,1)

        val resource = Resource("1", "alex","OPAP", start, 100,  rc.loadHolidays())
        resource.addLeave(LocalDate.of(2023,12,23), LocalDate.of(2024,1,12))

        val res =   resource.calculateWorkingDays(LocalDate.of(2024,1,31))
        assertEquals(13, res)
    }

    @Test
    fun shouldCalculateEndOfContract() {
        val rc = ResourceLoader(OfficeHolidaysCalendarProvider())
        val start = LocalDate.of(2024,1,1)

        val resource = Resource("1", "alex","OPAP", start, 1,  rc.loadHolidays())

        println( resource.estimateEndOfContract(LocalDate.of(2024,1,3)))

    }


}