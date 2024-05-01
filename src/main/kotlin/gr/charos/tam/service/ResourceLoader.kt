package gr.charos.tam.service

import net.fortuna.ical4j.model.component.CalendarComponent
import net.fortuna.ical4j.model.property.DtStart
import java.time.LocalDate
import java.time.temporal.Temporal

class ResourceLoader(calendarProvider : CalendarProvider) {
    private val cp = calendarProvider


    fun loadHolidays() : List<LocalDate> {
        return cp.calendar().getComponents<CalendarComponent>().map { c-> LocalDate.from(c.getProperty<DtStart<Temporal>>("DTSTART").get().date) }
    }



}