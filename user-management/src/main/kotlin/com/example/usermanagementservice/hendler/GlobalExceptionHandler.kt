package com.example.usermanagementservice.hendler

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Slf4j
class GlobalExceptionHandler (){

    @ExceptionHandler(CustomException::class)
    fun BadRequestExceptin(ex: CustomException): ResponseEntity<String> {
        println("inside Badexceptin handler")
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<String> {
        println("inside Badexceptin handler")
        return ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}