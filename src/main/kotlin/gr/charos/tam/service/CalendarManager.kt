package gr.charos.tam.service

import net.fortuna.ical4j.model.Calendar

interface CalendarManager {
    fun createCalendar():Calendar

}