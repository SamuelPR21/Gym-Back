package com.samu.aprendizaje.gym.trainner.controllers

import com.samu.aprendizaje.gym.trainner.dto.routines.DtoRequest
import com.samu.aprendizaje.gym.trainner.dto.routines.DtoResponse
import com.samu.aprendizaje.gym.trainner.service.RoutineService
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
@RequestMapping("/routine")
class RoutineController(private val routineService: RoutineService) {

    @PostMapping("/create")
    fun createRoutineByUserId(@RequestBody dto: DtoRequest): ResponseEntity<DtoResponse> =
         ResponseEntity.status(HttpStatus.CREATED).body(routineService.createRoutineByUserId(dto))



    @GetMapping("{id}")
    fun getRoutineById(@PathVariable id: Long?): ResponseEntity<DtoResponse> {
        if (id == null) {
            throw IllegalArgumentException("El ID no puede ser nulo")
        }
        return ResponseEntity.ok(routineService.getRoutineById(id))
    }

    @PatchMapping("{id}")
    fun updateRoutine(@PathVariable id: Long, @RequestBody fields: Map<String, Any>): ResponseEntity<DtoResponse> =
        ResponseEntity.ok(routineService.updateRoutine(id, fields))

    @DeleteMapping("{id}")
    fun deleteRoutine(@PathVariable id: Long): ResponseEntity<Void> {
        routineService.deleteRoutine(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/user/{userId}/routines")
    fun getRoutinesByUserId(@PathVariable userId: Long): ResponseEntity<List<DtoResponse>> {
        val routines = routineService.allRoutinesByUserId(userId)
        return ResponseEntity.ok(routines)
    }
}
