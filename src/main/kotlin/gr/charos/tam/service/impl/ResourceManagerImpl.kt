package gr.charos.tam.service.impl

import gr.charos.tam.domain.Resource
import gr.charos.tam.repository.ResourceRepository
import gr.charos.tam.service.ResourceManager
import java.time.LocalDate

class ResourceManagerImpl(repository : ResourceRepository) : ResourceManager {
    val repo = repository

    override fun addLeaves(resourceId: String, from: LocalDate, to: LocalDate) {
        var r = repo.get(resourceId)

        r?.addLeave(from, to)

        if (r!=null) {
            repo.save(r)
        }
    }

    override fun getAllResources(): List<Resource> {
        return repo.list()
    }

    override fun addResource(resource: Resource) {
        repo.save(resource)
    }

    override fun getResourcesExpiringWithin(days: Int): List<Resource> {
        val resources = repo.list()
        return resources.filter { r -> r.daysRemaining(LocalDate.now()) <= days }

    }
}