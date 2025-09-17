package com.samu.aprendizaje.gym.trainner.service

import com.samu.aprendizaje.gym.trainner.dto.Auth.AuthLoginRequest
import com.samu.aprendizaje.gym.trainner.dto.user.DtoRequest
import com.samu.aprendizaje.gym.trainner.dto.user.DtoResponse
import com.samu.aprendizaje.gym.trainner.models.User
import com.samu.aprendizaje.gym.trainner.repository.UserRepository
import com.samu.aprendizaje.gym.trainner.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class ServiceUser(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
) {

    fun create(dto: DtoRequest): DtoResponse {
        val user = User(
            name = dto.name,
            username = dto.username,
            email = dto.email,
            password = dto.password,
            currentWeights = dto.currentWeights,
            goalWeights = dto.goalWeights,
            dateOfBirth = dto.dateOfBirth
        )
        return userRepository.save(user).toResponse()
    }

    fun findAll(): List<DtoResponse> =
        userRepository.findAll().map { it.toResponse() }

    fun findById(id: Long): DtoResponse =
        userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User not found with id: $id") }
            .toResponse()

    fun update(id: Long, dto: Map<String, Any>): DtoResponse {
        val user = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User not found with id: $id") }
        val updatedUser = user.copy(
            name = dto["name"] as? String ?: user.name,
            username = dto["username"] as? String ?: user.username,
            email = dto["email"] as? String ?: user.email,
            password = dto["password"] as? String ?: user.password,
            currentWeights = dto["currentWeights"] as? Int ?: user.currentWeights,
            goalWeights = dto["goalWeights"] as? Int ?: user.goalWeights,
        )
        return userRepository.save(updatedUser).toResponse()
    }

    fun delete(id: Long) {
        if (!userRepository.existsById(id)) {
            throw NoSuchElementException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }

    fun registerOrCreateUser(dto: DtoRequest): DtoResponse {
        if (userRepository.findByEmail(dto.email) != null) {
            throw IllegalArgumentException("El email ya está registrado")
        }

        if (userRepository.findByUsername(dto.username) != null) {
            throw IllegalArgumentException("El username ya está registrado")
        }

        val user = User(
            name = dto.name,
            username = dto.username,
            email = dto.email,
            password = passwordEncoder.encode(dto.password),
            currentWeights = dto.currentWeights,
            goalWeights = dto.goalWeights,
            dateOfBirth = dto.dateOfBirth,
        )

        return userRepository.save(user).toResponse()
    }

    fun login(request: AuthLoginRequest): String {
        val authentication = UsernamePasswordAuthenticationToken(
            request.username,
            request.password
        )
        authenticationManager.authenticate(authentication)

        val user = userRepository.findByUsername(request.username)
            ?: throw UsernameNotFoundException("User not found")

        return jwtService.generateToken(user)
    }

    private fun User.toResponse() = DtoResponse(
        id, name, username, email, password, currentWeights, goalWeights, dateOfBirth
    )
}