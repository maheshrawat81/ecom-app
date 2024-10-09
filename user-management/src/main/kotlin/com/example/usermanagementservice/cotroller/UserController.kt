package com.example.usermanagementservice.cotroller

import com.example.usermanagementservice.model.User
import com.example.usermanagementservice.service.UserService
import com.example.usermanagementservice.jwt.JwtUtil
import com.example.usermanagementservice.wrappers.AuthRequest
import com.example.usermanagementservice.wrappers.AuthResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController @Autowired constructor(
    private val userService: UserService,
    private val jwtUtil: JwtUtil
) {

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
        // If authentication is successful, generate the JWT token
        val findUserByUsername = userService.findUserByUsername(authRequest.username)
            ?: throw RuntimeException("User not found")
        val token = jwtUtil.generateToken(findUserByUsername.username)
        return ResponseEntity.ok(AuthResponse(token))
    }
}
