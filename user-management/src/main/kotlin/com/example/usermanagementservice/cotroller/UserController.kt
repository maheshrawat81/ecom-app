package com.example.usermanagementservice.cotroller

import com.example.usermanagementservice.model.User
import com.example.usermanagementservice.service.UserService
import com.example.usermanagementservice.jwt.JwtUtil
import com.example.usermanagementservice.repo.UserRepo
import com.example.usermanagementservice.wrappers.AuthRequest
import com.example.usermanagementservice.wrappers.AuthResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@Slf4j
class UserController @Autowired constructor(
    private val userService: UserService,
    private val jwtUtil: JwtUtil,
    private val userRepo: UserRepo
) {
    private val passwordEncoder = BCryptPasswordEncoder()

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userService.registerUser(user)
        return ResponseEntity.ok(createdUser)
    }

    @GetMapping("/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<User> {
        val user = userService.findUserByUsername(username)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        val findUserByUsername = userService.validateUserByUsername(authRequest)
        println(findUserByUsername)
        val token = findUserByUsername?.let { jwtUtil.generateToken(it.username) }
        return ResponseEntity.ok(token?.let { AuthResponse(it) })
    }
}
