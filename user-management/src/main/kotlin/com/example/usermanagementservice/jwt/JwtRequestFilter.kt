package com.example.usermanagementservice.jwt

import com.example.usermanagementservice.hendler.CustomException
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Slf4j
class JwtRequestFilter @Autowired constructor(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    @Throws(ServletException::class, java.io.IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            println("Filter executed")
            val authorizationHeader = request.getHeader("Authorization")
            if (authorizationHeader != null) {
                authenticateToken(authorizationHeader, request)
            }
            chain.doFilter(request, response)
        } catch (e: Exception) {
            println(e.stackTrace)
            throw CustomException("Invalid user");
        }

    }

    private fun authenticateToken(authorizationHeader: String, request: HttpServletRequest): Boolean {
        try {
            if (!authorizationHeader.startsWith("Bearer ")) {
                throw CustomException("invalid token")
            }
            val jwt = authorizationHeader.substring(7)
            val username = jwtUtil.getSubjectFromToken(jwt)
            if (SecurityContextHolder.getContext().authentication == null) {
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                if (jwtUtil.isValidToken(jwt.toString())) {
                    println("going to add Authentication")
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
            return true
        } catch (e: Exception) {
            println(e.stackTrace)
            throw CustomException("Invalid user");
        }
    }
}
