package gr.charos.tam.service.impl

import gr.charos.tam.service.CalendarManager
import gr.charos.tam.service.ResourceManager
import net.fortuna.ical4j.data.CalendarOutputter
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.ComponentList
import net.fortuna.ical4j.model.Property
import net.fortuna.ical4j.model.PropertyList
import net.fortuna.ical4j.model.component.VAlarm
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.*
import net.fortuna.ical4j.model.property.immutable.ImmutableCalScale
import net.fortuna.ical4j.model.property.immutable.ImmutableVersion
import net.fortuna.ical4j.util.RandomUidGenerator
import java.io.FileOutputStream
import java.time.Duration
import java.time.LocalDate
import java.time.temporal.Temporal
import java.util.*


class CalendarManagerImpl(rm : ResourceManager) : CalendarManager {
    val resourceManager = rm;

    override fun createCalendar(): Calendar {
       val events =   resourceManager.getResourcesExpirations()
            .map {r -> r.toEvent()
                }

        val c = ComponentList<VEvent>()
        c.addAll(events)

        val calendar = Calendar()
        calendar.add(ProdId("-//Alex Charos//TAM beta//EN"))
        calendar.add(ImmutableVersion.VERSION_2_0)
        calendar.add(ImmutableCalScale.GREGORIAN)

        events.forEach{e ->calendar.add(e)}

        val fout = FileOutputStream("tam.ics")

        val outputter = CalendarOutputter()
        outputter.output(calendar, fout)



        return calendar

    }
}