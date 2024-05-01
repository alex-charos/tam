package gr.charos.tam.service.impl

import gr.charos.tam.domain.Report
import gr.charos.tam.domain.Resource
import gr.charos.tam.domain.ResourceExpiration
import gr.charos.tam.repository.ResourceRepository
import gr.charos.tam.service.CalendarProvider
import gr.charos.tam.service.ResourceCalculator
import gr.charos.tam.service.ResourceManager
import java.time.LocalDate

class ResourceManagerImpl(repository : ResourceRepository, cp : CalendarProvider) : ResourceManager {
    val repo = repository
    val holidays = cp.holidays();


    override fun addLeaves(resourceId: String, from: LocalDate, to: LocalDate) {
        val r = repo.get(resourceId)

        r?.addLeave(from, to)

        if (r!=null) {
            repo.save(r)
        }
    }

    override fun addExtensionInDays(resourceId: String, days: Int) {
        val r = repo.get(resourceId)
        r?.addExtension(days)

        if (r!=null) {
            repo.save(r)
        }

    }

    override fun getAllResources(): List<Resource> {
        return repo.list()
    }

    override fun saveResource(resource: Resource) {
        repo.save(resource)
    }

    override fun getResourcesExpiringWithin(days: Int): List<Resource> {
        val resources = repo.list()
        return resources
            .map{ r -> ResourceCalculator(r, holidays)}
            .filter { r -> r.daysRemaining(LocalDate.now()) <= days }
            .map { r -> r.resource}

    }

    override fun getResourcesExpirations(): List<ResourceExpiration> {
       return repo.list()
            .map { r-> ResourceCalculator(r, holidays) }
            .map { r-> ResourceExpiration(r.resource,r.endOfContract()) }
           .sortedBy { r->r.expirationDate }
    }

    override fun getResourcesReport(from: LocalDate, to: LocalDate): List<Report> {
        return repo.list()
            .map { r ->ResourceCalculator(r,holidays) }
            .map { r->r.calculateReport(from,to) }
    }


}