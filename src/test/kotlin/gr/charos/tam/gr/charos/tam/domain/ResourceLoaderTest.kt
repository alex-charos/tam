package gr.charos.tam.gr.charos.tam.domain

import gr.charos.tam.service.ResourceLoader
import gr.charos.tam.service.impl.OfficeHolidaysCalendarProvider
import org.junit.jupiter.api.Test

class ResourceLoaderTest {

    @Test
    fun loadHolidays() {
        val resourceLoader = ResourceLoader(OfficeHolidaysCalendarProvider("https://www.officeholidays.com/ics/greece"))
        resourceLoader.loadHolidays()
    }
}