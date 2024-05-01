package gr.charos.tam.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import gr.charos.tam.repository.ResourceRepository
import gr.charos.tam.repository.impl.JSONFileRepository
import gr.charos.tam.service.CalendarManager
import gr.charos.tam.service.CalendarProvider
import gr.charos.tam.service.ResourceManager
import gr.charos.tam.service.impl.CalendarManagerImpl
import gr.charos.tam.service.impl.OfficeHolidaysCalendarProvider
import gr.charos.tam.service.impl.ResourceManagerImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val om = jacksonObjectMapper()
        om.registerModules(JavaTimeModule())
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        return om
    }

    @Bean
    fun resourceRepository(@Value("\${tam.repository.location}") location : String, om : ObjectMapper) : ResourceRepository {
        return JSONFileRepository(location, om);

    }

    @Bean
    fun resourceManager(resourceRepository : ResourceRepository, calendarProvider: CalendarProvider) : ResourceManager {
        return ResourceManagerImpl(resourceRepository, calendarProvider);
    }

    @Bean
    fun calendarProvider(@Value("\${tam.calendar.url}") url :String) : CalendarProvider {
        return OfficeHolidaysCalendarProvider(url);
    }

    @Bean
    fun calendarManager(rm : ResourceManager) : CalendarManager {
        return CalendarManagerImpl(rm)
    }
}