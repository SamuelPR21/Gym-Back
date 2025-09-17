package com.samu.aprendizaje.gym.trainner.security

import com.samu.aprendizaje.gym.trainner.repository.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.aspectj.weaver.tools.cache.SimpleCacheFactory.path
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter (private val jwtService: JwtService, private val userRepository: UserRepository)
    : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {


        val path = request.servletPath
        if (path.startsWith("/users/login") || path.startsWith("/users/create")) {
            filterChain.doFilter(request, response)
            return
        }


        val authHeader = request.getHeader("Authorization") ?: return filterChain.doFilter(request, response)
        if (!authHeader.startsWith("Bearer ")) return filterChain.doFilter(request, response)

        val token = authHeader.substring(7)
        val username = jwtService.extractUsername(token)
        val user  = userRepository.findByUsername(username)

        if (user != null) {
            val auth = UsernamePasswordAuthenticationToken(user, null )
            SecurityContextHolder.getContext().authentication = auth
        }

        filterChain.doFilter(request, response)

    }
}