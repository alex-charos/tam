package gr.charos.tam.gr.charos.tam.domain

import gr.charos.tam.domain.ResourceLoader
import gr.charos.tam.service.impl.OfficeHolidaysCalendarProvider
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ResourceLoaderTest {

    @Test
    fun loadHolidays() {
        val resourceLoader = ResourceLoader(OfficeHolidaysCalendarProvider())
        resourceLoader.loadHolidays()
    }
}