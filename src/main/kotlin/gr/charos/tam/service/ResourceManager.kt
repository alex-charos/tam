package gr.charos.tam.service

import gr.charos.tam.domain.Report
import gr.charos.tam.domain.Resource
import gr.charos.tam.domain.ResourceExpiration
import java.time.LocalDate

interface ResourceManager {
    fun addLeaves(resourceId:String, from: LocalDate, to:LocalDate)

    fun addExtensionInDays(resourceId:String, days:Int)

    fun getAllResources(): List<Resource>

    fun saveResource(resource: Resource)

    fun getResourcesExpiringWithin(days:Int) : List<Resource>

    fun getResourcesExpirations() : List<ResourceExpiration>

    fun getResourcesReport(from:LocalDate, to:LocalDate) : List<Report>

}