package gr.charos.tam.service

import net.fortuna.ical4j.model.Calendar
import java.time.LocalDate

interface CalendarProvider {

    fun calendar(): Calendar
    fun holidays(): List<LocalDate>
}