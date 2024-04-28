package gr.charos.tam.repository.impl

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import gr.charos.tam.domain.Resource
import org.junit.jupiter.api.Test
import java.time.LocalDate

class JSONFileRepositoryTest {

    @Test
    fun save() {
        val om = jacksonObjectMapper()
        om.registerModules(JavaTimeModule())
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        val repository = JSONFileRepository("/Users/alexandroscharos/projects/tam/", om)
        val res = Resource("1","Alex","OPAP",LocalDate.now(),123, listOf(LocalDate.now()))
        res.addLeave(LocalDate.now().minusDays(23))
        repository.save(res)

      println( repository.get("1").toString())
        println(repository.get("1")?.leaves)

    }


    @Test
    fun list(){
        val om = jacksonObjectMapper()
        om.registerModules(JavaTimeModule())
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        val repository = JSONFileRepository("/Users/alexandroscharos/projects/tam/", om)
        repository.list().forEach { r: Resource -> println(r.name) }

    }


}