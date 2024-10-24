package com.example.product_catalog.model

import org.ektorp.support.CouchDbDocument
import com.fasterxml.jackson.annotation.JsonProperty

data class Product(
    @JsonProperty("name") var name: String,            // Added @JsonProperty annotation
    @JsonProperty("seller_name") var seller_name: String,
    @JsonProperty("price") var price: Double,          // Added @JsonProperty annotation
    @JsonProperty("quantity") var quantity: Int,       // Added @JsonProperty annotation
    @JsonProperty("description") var description: String? = null // Added @JsonProperty annotation
): CouchDbDocument(){

}