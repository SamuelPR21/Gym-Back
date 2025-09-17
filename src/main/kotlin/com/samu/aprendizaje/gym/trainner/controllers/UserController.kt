package com.samu.aprendizaje.gym.trainner.controllers

import com.samu.aprendizaje.gym.trainner.dto.Auth.AuthLoginRequest
import com.samu.aprendizaje.gym.trainner.dto.Auth.AuthLoginResponse
import com.samu.aprendizaje.gym.trainner.dto.user.DtoRequest
import com.samu.aprendizaje.gym.trainner.dto.user.DtoResponse
import com.samu.aprendizaje.gym.trainner.service.ServiceUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val serviceUser: ServiceUser) {

    @PostMapping("/create")
    fun createUser(@RequestBody dto: DtoRequest): ResponseEntity<DtoResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(serviceUser.registerOrCreateUser(dto))

    @GetMapping("/{id}")
    fun finUserById(@PathVariable id: Long): ResponseEntity<DtoResponse> =
        ResponseEntity.ok(serviceUser.findById(id))

    @GetMapping("/all")
    fun findAllUsers(): ResponseEntity<List<DtoResponse>> =
        ResponseEntity.ok(serviceUser.findAll())

    @PatchMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody fields: Map<String, Any>): ResponseEntity<DtoResponse> =
        ResponseEntity.ok(serviceUser.update(id, fields))

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        serviceUser.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/login")
    fun login(@RequestBody dto: AuthLoginRequest): ResponseEntity<AuthLoginResponse> {
        val token = serviceUser.login(dto)
        return ResponseEntity.ok(AuthLoginResponse(token))
    }


}