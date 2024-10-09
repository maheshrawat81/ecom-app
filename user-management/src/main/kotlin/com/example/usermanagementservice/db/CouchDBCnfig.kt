package com.example.usermanagementservice.db

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.ektorp.CouchDbConnector
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.ektorp.CouchDbInstance
import org.ektorp.http.HttpClient
import org.ektorp.http.StdHttpClient
import org.ektorp.impl.StdCouchDbInstance
import org.ektorp.impl.StdObjectMapperFactory

@Configuration
class CouchDbConfig {

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
            .url("http://127.0.0.1:5984").username("root").password("root")
            .build()

        // Create CouchDbInstance using the custom ObjectMapperFactory
        val dbInstance = StdCouchDbInstance(httpClient, objectMapperFactory)

        // Return a CouchDbConnector to interact with a specific database
        return dbInstance.createConnector("ecom", true)
    }

}