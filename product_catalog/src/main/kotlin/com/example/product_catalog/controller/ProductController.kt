package com.example.product_catalog.controller

import com.example.product_catalog.model.Product
import com.example.product_catalog.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {

    @PostMapping
    fun addProduct(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.addProduct(product))
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: String, @RequestBody product: Product): ResponseEntity<Product?> {
        val updatedProduct = productService.updateProduct(id, product)
        return if (updatedProduct != null) {
            ResponseEntity.ok(updatedProduct)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: String): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.getAllProducts())
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: String): ResponseEntity<Product?> {
        val product = productService.getProductById(id)
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
