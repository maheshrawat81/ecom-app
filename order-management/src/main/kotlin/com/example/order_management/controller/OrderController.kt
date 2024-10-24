package com.example.order_management.controller

import com.example.order_management.model.Order
import com.example.order_management.service.OrderService
import com.example.order_management.webclient.HttpService
import jakarta.servlet.http.HttpServletRequest
import org.ektorp.ViewResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService, private val httpService: HttpService) {

    @PostMapping("/add")
    fun addProduct(
        http: HttpServletRequest,
        @RequestBody productIds: Map<String, Int>,
        @RequestParam username: String
    ): ResponseEntity<String> {
        val orders: List<Order> = productIds.map { (productId, quantity) ->
            Order(
                UUID.randomUUID().toString(),
                null,
                productId,
                username,
                quantity
            )
        }
        var addOrder = orderService.addOrder(orders)
        return ResponseEntity.ok(addOrder)
    }
    @GetMapping
    fun getOrderByUser(@RequestParam username : String) :MutableList<ViewResult.Row>?{
        return orderService.getOrdersByUser(username);
    }
}