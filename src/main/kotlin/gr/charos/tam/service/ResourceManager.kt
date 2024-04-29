package gr.charos.tam.service

import gr.charos.tam.domain.Resource
import java.time.LocalDate

interface ResourceManager {
    fun addLeaves(resourceId:String, from: LocalDate, to:LocalDate)

    fun getAllResources(): List<Resource>

    fun addResource(resource: Resource)

    fun getResourcesExpiringWithin(days:Int) : List<Resource>

}