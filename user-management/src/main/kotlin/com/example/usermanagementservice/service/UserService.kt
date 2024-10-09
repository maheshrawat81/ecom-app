package com.example.usermanagementservice.service


import com.example.usermanagementservice.model.User
import com.example.usermanagementservice.hendler.CustomException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor() : UserDetailsService {

    private val passwordEncoder = BCryptPasswordEncoder()

    //Todo need to integrate database
    val newUser = User(
        username = "mahesh",
        email = "mahesh.com",
        password = "Mahesh"
    )


    fun registerUser(user: User): User {
        newUser.username=user.username
        newUser.email=user.email
        newUser.password = passwordEncoder.encode(user.password)
        return newUser;
    }

    fun findUserByUsername(username: String): User? {
        return if (username == newUser.username) {
            newUser;
        } else {
            throw CustomException("USER NT FUND : $username");
        }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val findUserByUsername = findUserByUsername(username)
        if (findUserByUsername != null) {
            return org.springframework.security.core.userdetails.User(
                findUserByUsername.username,
                findUserByUsername.password,
                emptyList()
            )
        }
        throw CustomException("USER NT FUND : $username");
    }
}
