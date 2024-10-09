package com.example.usermanagementservice.hendler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
    class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun BadRequestExceptin(ex: CustomException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}