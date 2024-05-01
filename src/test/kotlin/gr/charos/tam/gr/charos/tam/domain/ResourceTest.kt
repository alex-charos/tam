package gr.charos.tam.gr.charos.tam.domain

import gr.charos.tam.domain.Resource
import gr.charos.tam.service.ResourceCalculator
import gr.charos.tam.service.ResourceLoader
import gr.charos.tam.service.impl.OfficeHolidaysCalendarProvider
import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Component
import net.fortuna.ical4j.model.ComponentList
import net.fortuna.ical4j.model.Property
import net.fortuna.ical4j.model.TimeZoneRegistryImpl
import net.fortuna.ical4j.model.component.CalendarComponent
import net.fortuna.ical4j.model.component.VAlarm
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.Action
import net.fortuna.ical4j.model.property.Description
import net.fortuna.ical4j.model.property.Repeat
import net.fortuna.ical4j.model.property.Trigger
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import java.io.FileInputStream
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

class ResourceTest {

    @Test
    fun test() {

        val calBuilder = CalendarBuilder(TimeZoneRegistryImpl())
        val c =calBuilder.build(FileInputStream("src/test/resources/test.ics"))

        val a =   c.getComponents<VEvent>()
            .map { event -> event.alarms }
            .map { a -> a }
            .first()

        println(a.first().propertyList)
        /*
        TRIGGER:-P1W
REPEAT:1
DURATION:PT15M
ACTION:DISPLAY
DESCRIPTION:Reminder


BEGIN:VCALENDAR
BEGIN:VEVENT
DTSTART;VALUE=DATE:20240513
SUMMARY:test-tesr
UID:test-tesr
BEGIN:VALARM
TRIGGER:-P7D
REPEAT:1
DURATION:PT15M
ACTION:DISPLAY
DESCRIPTION:Reminder
END:VALARM
END:VEVENT
END:VCALENDAR

         */
       // var triggerP = Property(Property.TRIGGER, )
    var alarm = VAlarm()

        alarm.add(Trigger(Duration.ofDays(2).negated()))
        alarm.add(net.fortuna.ical4j.model.property.Duration (Duration.ofMinutes(15)))
        alarm.add(Repeat(1))
        alarm.add(Action("DISPLAY"))
        alarm.add(Description("Reminder!!!!"))


    }


//
//    @Test
//    fun shouldCalculateWorkingDays() {
//        val rc = ResourceLoader(OfficeHolidaysCalendarProvider())
//        val start = LocalDate.of(2024,1,1)
//
//      //  val resource = Resource("1", "alex","OPAP", start, 100,  rc.loadHolidays())
//
//
//        //val res =   ResourceCalculator( resource, .calculateWorkingDays(LocalDate.of(2024,1,31))
//        //assertEquals(22, res)
//
//
//    }
//
//    @Test
//    fun shouldCalculateWorkingDaysWithSingleDayLeave() {
//        val rc = ResourceLoader(OfficeHolidaysCalendarProvider())
//        val start = LocalDate.of(2024,1,1)
//
//        val resource = Resource("1", "alex","OPAP", start, 100,  rc.loadHolidays())
//        resource.addLeave(LocalDate.of(2024,1,2), LocalDate.of(2024,1,2))
//
//       // val res =   resource.calculateWorkingDays(LocalDate.of(2024,1,31))
//        //assertEquals(21, res)
//    }
//
//
//    @Test
//    fun shouldCalculateWorkingDaysWithMultipleDaysLeave() {
//        val rc = ResourceLoader(OfficeHolidaysCalendarProvider())
//        val start = LocalDate.of(2024,1,1)
//
//        val resource = Resource("1", "alex","OPAP", start, 100,  rc.loadHolidays())
//        resource.addLeave(LocalDate.of(2023,12,23), LocalDate.of(2024,1,12))
//
//     //   val res =   resource.calculateWorkingDays(LocalDate.of(2024,1,31))
//      //  assertEquals(13, res)
//    }
//
////    @Test
////    fun shouldCalculateEndOfContract() {
////        val rc = ResourceLoader(OfficeHolidaysCalendarProvider())
////        val start = LocalDate.of(2024,1,1)
////
////        val resource = Resource("1", "alex","OPAP", start, 1,  rc.loadHolidays())
////
////        println( resource.estimateEndOfContract(LocalDate.of(2024,1,3)))
////
////    }


}