package com.example.order_management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class OrderManagementApplication

fun main(args: Array<String>) {
	runApplication<OrderManagementApplication>(*args)
}