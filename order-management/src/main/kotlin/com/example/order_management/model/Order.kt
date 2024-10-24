package com.example.order_management.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Order(
    @JsonProperty("_id") var id: String? = null,      // CouchDB's _id field
    @JsonProperty("_rev") var revision: String? = null, // CouchDB's _rev field
    @JsonProperty("productId") var productId: String,
    @JsonProperty("username") var username: String,
    @JsonProperty("quantity") var quantity: Int,
)