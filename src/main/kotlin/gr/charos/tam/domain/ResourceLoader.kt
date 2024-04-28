package gr.charos.tam.domain

import gr.charos.tam.service.CalendarProvider
import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.Component
import net.fortuna.ical4j.model.TimeZoneRegistryImpl
import net.fortuna.ical4j.model.component.CalendarComponent
import net.fortuna.ical4j.model.property.DtStart
import java.io.FileInputStream
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDate
import java.time.temporal.Temporal
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class ResourceLoader(calendarProvider : CalendarProvider) {
    private val cp = calendarProvider


    fun loadHolidays() : List<LocalDate> {
        return cp.calendar().getComponents<CalendarComponent>().map { c-> LocalDate.from(c.getProperty<DtStart<Temporal>>("DTSTART").get().date) }
    }



}