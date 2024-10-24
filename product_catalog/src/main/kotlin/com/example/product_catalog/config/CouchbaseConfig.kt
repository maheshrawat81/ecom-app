package com.example.product_catalog.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.ektorp.CouchDbConnector
import org.ektorp.CouchDbInstance
import org.ektorp.http.HttpClient
import org.ektorp.http.StdHttpClient
import org.ektorp.impl.StdCouchDbConnector
import org.ektorp.impl.StdCouchDbInstance
import org.ektorp.impl.StdObjectMapperFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CouchDBConfig {

    @Value("\${couchdb.url}")
    private lateinit var couchDbUrl: String

    @Value("\${couchdb.username}")
    private lateinit var couchDbUsername: String

    @Value("\${couchdb.password}")
    private lateinit var couchDbPassword: String


    @Bean
    fun couchDbConnector(): CouchDbConnector {
        // Custom ObjectMapperFactory to handle Jackson serialization
        val objectMapperFactory = object : StdObjectMapperFactory() {
            override fun createObjectMapper(): ObjectMapper {
                val objectMapper = super.createObjectMapper()
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                return objectMapper
            }
        }

        // Create an HTTP client with the custom ObjectMapperFactory


        val httpClient: HttpClient = StdHttpClient.Builder()
            .url(couchDbUrl).username(couchDbUsername).password(couchDbPassword)
            .build()

        // Create CouchDbInstance using the custom ObjectMapperFactory
        val dbInstance = StdCouchDbInstance(httpClient, objectMapperFactory)

        // Return a CouchDbConnector to interact with a specific database
        return dbInstance.createConnector("product_catalog", true)
    }
}
