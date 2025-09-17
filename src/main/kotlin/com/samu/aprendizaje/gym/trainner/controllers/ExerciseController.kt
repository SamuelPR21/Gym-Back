package com.samu.aprendizaje.gym.trainner.controllers

import com.samu.aprendizaje.gym.trainner.dto.exercise.ExerciseDtoRequest
import com.samu.aprendizaje.gym.trainner.dto.exercise.ExerciseDtoResponse
import com.samu.aprendizaje.gym.trainner.service.ExerciseService
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
@RequestMapping("/exercise")
class ExerciseController(private val exerciseService: ExerciseService) {

    @PostMapping("/create")
    fun createExercise(@RequestBody dto: ExerciseDtoRequest): ResponseEntity<ExerciseDtoResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.createExercise(dto))

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long?): ResponseEntity<ExerciseDtoResponse> {
        if (id == null) {
            throw IllegalArgumentException("El ID no puede ser nulo")
        }
        return ResponseEntity.ok(exerciseService.getExerciseById(id))

    }

    @PatchMapping("/{id}")
    fun updateExercise(@PathVariable id: Long, @RequestBody fields: Map<String, Any>): ResponseEntity<ExerciseDtoResponse> =
        ResponseEntity.ok(exerciseService.updateExercise(id, fields))

    @DeleteMapping("/{id}")
    fun deleteExercise(@PathVariable id: Long): ResponseEntity<Void> {
        exerciseService.deleteExercise(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/all")
    fun getAllExercises(): ResponseEntity<List<ExerciseDtoResponse>> =
        ResponseEntity.ok(exerciseService.getAllExercises())

    @GetMapping("/muscle-group/{muscleGroup}")
    fun listExercisesByMuscleGroup(@PathVariable muscleGroup: String): ResponseEntity<List<ExerciseDtoResponse>> =
        ResponseEntity.ok(exerciseService.listExercisesByMuscleGroup(muscleGroup))
}