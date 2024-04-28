package gr.charos.tam.repository

import gr.charos.tam.domain.Resource


interface ResourceRepository {
    fun save(resource: Resource): Resource
    fun get(id: String): Resource?
    fun list(): List<Resource>

}