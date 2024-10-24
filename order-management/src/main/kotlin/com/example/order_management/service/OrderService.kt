package com.example.order_management.service

import com.example.order_management.model.Order
import com.example.order_management.repository.OrderRepository
import org.ektorp.ViewResult
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {

    fun addOrder(orders: List<Order>) : String {
        // Generate a unique ID if it's not provided
        orders.forEach{order: Order ->  orderRepository.add(order)}
        return "Order Created"
    }

    fun getOrdersByUser(username: String): MutableList<ViewResult.Row>? {
        return orderRepository.findByUsername(username)
    }
}