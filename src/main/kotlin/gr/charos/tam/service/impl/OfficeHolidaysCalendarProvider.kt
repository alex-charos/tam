package gr.charos.tam.service.impl

import gr.charos.tam.service.CalendarProvider
import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.TimeZoneRegistryImpl
import net.fortuna.ical4j.model.component.CalendarComponent
import net.fortuna.ical4j.model.property.DtStart
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDate
import java.time.temporal.Temporal

class OfficeHolidaysCalendarProvider(url : String) : CalendarProvider {

   private val calendarUrl = url

    override fun calendar(): Calendar {
        val req = HttpRequest.newBuilder().uri(URI(calendarUrl)).GET().build()

        val response = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofInputStream())

        val calBuilder = CalendarBuilder(TimeZoneRegistryImpl())

       return  calBuilder.build(response.body())
    }

    override fun holidays(): List<LocalDate> {
        return calendar().getComponents<CalendarComponent>().map { c-> LocalDate.from(c.getProperty<DtStart<Temporal>>("DTSTART").get().date) }
    }
}