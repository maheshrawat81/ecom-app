package com.example.usermanagementservice.repo

import com.example.usermanagementservice.hendler.CustomException
import com.example.usermanagementservice.model.User
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.ektorp.CouchDbConnector
import org.ektorp.support.CouchDbRepositorySupport
import org.ektorp.support.GenerateView
import org.springframework.stereotype.Repository

@Repository
@Slf4j
class UserRepo(db: CouchDbConnector) : CouchDbRepositorySupport<User>(User::class.java, db) {

    @GenerateView
    fun findByUsername(username: String): User {
        try {
            val query = createQuery("by_username")
                .key(username).limit(1)
            val user = db.queryView(query)
            val rows = user.rows
            if (rows.isEmpty()) {
                throw CustomException("User Not Found")
            }
            val value = rows[0].value
            val objectMapper = ObjectMapper()
            val userMap: User = objectMapper.readValue(value, User::class.java)
            return userMap
        } catch (ex: Exception) {
            log.info("exception cause : {}", ex.stackTrace)
            throw ex.message?.let { CustomException(it) }!!
        }
    }
}

data class CouchDBResponse(
    val rows: List<CouchDBRow>
)

data class CouchDBRow(
    val key: String,
    val value: User
)