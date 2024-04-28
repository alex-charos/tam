package gr.charos.tam.service.impl

import gr.charos.tam.service.CalendarProvider
import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.TimeZoneRegistryImpl
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class OfficeHolidaysCalendarProvider : CalendarProvider {

    val HOLIDAYS_URL ="https://www.officeholidays.com/ics/greece"

    override fun calendar(): Calendar {
        val req = HttpRequest.newBuilder().uri(URI(HOLIDAYS_URL)).GET().build()

        val response = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofInputStream())

        val calBuilder = CalendarBuilder(TimeZoneRegistryImpl())

       return  calBuilder.build(response.body())
    }
}