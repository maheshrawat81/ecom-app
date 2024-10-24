package com.example.usermanagementservice.service

import com.example.usermanagementservice.hendler.CustomException
import com.example.usermanagementservice.model.User
import com.example.usermanagementservice.repo.UserRepo
import com.example.usermanagementservice.wrappers.AuthRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserServiceTest {

    private lateinit var userService: UserService
    private lateinit var userRepo: UserRepo
    private val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder() // Use real instance for tests

    @BeforeEach
    fun setUp() {
        // Mock dependencies
        userRepo = mock(UserRepo::class.java)
        userService = UserService(userRepo) // Inject mock repo into service
    }

//    @Test
//    fun registerUser() {
//        // Arrange
//        val user = User(username = "testuser", password = "plainpassword", email = "rawat@gmail.com")
//
//        // Act
//        val registeredUser = userService.registerUser(user)
//
//        // Assert
//        assertNull(registeredUser.password) // Password should be nullified after registration
//        assertTrue(passwordEncoder.matches("plainpassword", user.password)) // Verify that the password was hashed
//        verify(userRepo).add(user) // Ensure the userRepo's add method was called
//    }
//
//    @Test
//    fun findUserByUsername() {
//        // Arrange
//        val user = User(username = "testuser", password = "hashedpassword", email = "rawat@gmail.com")
//        `when`(userRepo.findByUsername("testuser")).thenReturn(user)
//
//        // Act
//        val foundUser = userService.findUserByUsername("testuser")
//
//        // Assert
//        assertNotNull(foundUser)
//        assertEquals("testuser", foundUser?.username)
//    }


    @Test
    fun findUserByUsernamNull() {
        // Arrange
        `when`(userRepo.findByUsername("testuser")).thenReturn(null)

        // Act
        val foundUser = userService.findUserByUsername("testuser")

        // Assert
        assertNull(foundUser)
    }

    @Test
    fun validateUserByUsernameSuccess() {
        // Arrange
        val user = User(username = "testuser", password = passwordEncoder.encode("plainpassword"), email = "rawat@gmail.com")
        val authRequest = AuthRequest(username = "testuser", password = "plainpassword")
        `when`(userRepo.findByUsername("testuser")).thenReturn(user)

        // Act
        val validatedUser = userService.validateUserByUsername(authRequest)

        // Assert
        assertNotNull(validatedUser)
        assertEquals("testuser", validatedUser?.username)
    }

    @Test
    fun validateUserByUsernameExceptionCase() {

        val user = User(username = "testuser", password = passwordEncoder.encode("plainpassword"), email = "rawat@gmail.com")
        val authRequest = AuthRequest(username = "testuser", password = "wrongpassword")
        `when`(userRepo.findByUsername("testuser")).thenReturn(user)

        val exception = assertThrows(CustomException::class.java) {
            userService.validateUserByUsername(authRequest)
        }
        assertEquals("INVALID USER OR PASSWORD", exception.message)
    }
//
    @Test
    fun loadUserByUsernameSuccess() {
        // Arrange
        val user = User(username = "testuser", password = passwordEncoder.encode("plainpassword"), email = "rawat@gmail.com")
        `when`(userRepo.findByUsername("testuser")).thenReturn(user)

        // Act
        val userDetails: UserDetails = userService.loadUserByUsername("testuser")

        // Assert
        assertNotNull(userDetails)
        assertEquals("testuser", userDetails.username)
    }

    @Test
    fun loadUserByUsernameExcepetion() {
        // Arrange
        `when`(userRepo.findByUsername("testuser")).thenReturn(null)

        // Act & Assert
        val exception = assertThrows(CustomException::class.java) {
            userService.loadUserByUsername("testuser")
        }
        assertEquals("USER NT FUND : testuser", exception.message)
    }
}
