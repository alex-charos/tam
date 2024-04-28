package gr.charos.tam.repository.impl

import com.fasterxml.jackson.databind.ObjectMapper
import gr.charos.tam.domain.Resource
import gr.charos.tam.repository.ResourceRepository
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.walk


class JSONFileRepository(loc : String, om : ObjectMapper) : ResourceRepository  {

    private val objectMapper = om
    private val location = loc


    override fun save(resource: Resource): Resource {
        val s = objectMapper.writeValueAsString(resource)
        val path = buildFileForResource(resource.id)
        if (!Files.exists(path)) {
            Files.createFile(path)
        }
        Files.write(path, s.toByteArray())


        return resource
    }

    override fun get(id: String): Resource? {
        val s = Files.readString(buildFileForResource(id))
        return objectMapper.readValue(s, Resource::class.java)
    }

    override fun list(): List<Resource> {
        return Files.walk(Paths.get(location))
            .filter { p: Path -> Files.isRegularFile(p) }
            .filter { p: Path -> p.extension.equals("tam-json", ignoreCase = true) }
            .map { p: Path -> Files.readString(p) }
            .map { p : String -> objectMapper.readValue(p, Resource::class.java) }
            .toList()

    }

    private fun buildFileForResource(id: String): Path {
        return Paths.get("$location/$id.tam-json")
    }

}