package com.example.order_management.repository

import com.example.order_management.model.Order
import com.fasterxml.jackson.databind.ObjectMapper
import org.ektorp.CouchDbConnector
import org.ektorp.ViewResult
import org.ektorp.support.CouchDbRepositorySupport
import org.ektorp.support.GenerateView
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(private val db: CouchDbConnector) : CouchDbRepositorySupport<Order>(Order::class.java, db) {

    @GenerateView
    fun findByUsername(username: String): MutableList<ViewResult.Row>? {
        try {
            println("inside repos "+username)
            val query = createQuery("by_username")
                .key(username)
            val user = db.queryView(query)
            val rows = user.rows
            if (rows.isEmpty()) {
                throw RuntimeException("User Not Found")
            }
            return rows
        } catch (ex: Exception) {
            log.info("exception cause : {}", ex.stackTrace)
            throw RuntimeException("User Not Found")
        }
    }

}