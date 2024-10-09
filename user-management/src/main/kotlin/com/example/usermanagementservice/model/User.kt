package com.example.usermanagementservice.model


data class User(

    var id: String? = null,
    var username: String,
    var email: String,
    var password: String // This should be hashed in production
)
