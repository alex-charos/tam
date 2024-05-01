package gr.charos.tam.domain

import net.fortuna.ical4j.model.ComponentList
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.PropertyList
import net.fortuna.ical4j.model.TimeZoneRegistryFactory
import net.fortuna.ical4j.model.component.VAlarm
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.*
import net.fortuna.ical4j.util.RandomUidGenerator
import java.time.Duration
import java.time.LocalDate
import java.time.temporal.Temporal
import java.util.*


data class ResourceExpiration(val resource:Resource, val expirationDate: LocalDate) {


    fun toEvent() : VEvent {

        val alarm = VAlarm()
        alarm.add(Trigger(Duration.ofDays(14).negated()))
        alarm.add(net.fortuna.ical4j.model.property.Duration (Duration.ofMinutes(15)))
        alarm.add(Repeat(1))
        alarm.add(Action("DISPLAY"))
        alarm.add(Description("Reminder for ${resource.name} contract expiration"))

        var alarms = ComponentList<VAlarm>(listOf(alarm));

        val event: VEvent = VEvent(PropertyList(), alarms)
        event.add(DtStart<Temporal>(expirationDate))
        event.add(Summary("${resource.name} contract expiration"))
        event.add(Uid(resource.id))
        return event
    }
}