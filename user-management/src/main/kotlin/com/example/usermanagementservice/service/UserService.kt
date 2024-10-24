package com.example.usermanagementservice.service


import com.example.usermanagementservice.model.User
import com.example.usermanagementservice.hendler.CustomException
import com.example.usermanagementservice.repo.UserRepo
import com.example.usermanagementservice.wrappers.AuthRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor( val userRepo: UserRepo) : UserDetailsService {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun registerUser(user: User): User {
        user.password = passwordEncoder.encode(user.password)
        userRepo.add(user)
        return user
    }

    fun findUserByUsername(username: String): User? {
        val findByUsername = userRepo.findByUsername(username)
        return findByUsername;
    }

    fun validateUserByUsername(user: AuthRequest): User? {
        val findByUsername = userRepo.findByUsername(user.username)
        if (passwordEncoder.matches(user.password,findByUsername.password)){
            return findByUsername
        }
        throw CustomException("INVALID USER OR PASSWORD");
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

    fun updateEmail(id: String, email: String): User? {
        val user = userRepo.get(id)
        user.email=email
        userRepo.update(user)
        return user
    }
}
