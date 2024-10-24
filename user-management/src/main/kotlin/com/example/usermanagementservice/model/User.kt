package com.example.usermanagementservice.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.ektorp.support.CouchDbDocument


data class User @JsonCreator constructor(
    @JsonProperty("username") var username: String,
    @JsonProperty("email") var email: String,
    @JsonProperty("password") var password: String
) : CouchDbDocument(){

}
