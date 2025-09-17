package com.samu.aprendizaje.gym.trainner.controllers

import com.samu.aprendizaje.gym.trainner.dto.RoutineExercise.RotuineExerciseResponseDTO
import com.samu.aprendizaje.gym.trainner.dto.RoutineExercise.RoutineExerciseRequestDTO
import com.samu.aprendizaje.gym.trainner.service.RoutineExerciseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/routine-exercises")
class RoutineExerciseController(private val routineExerciseService: RoutineExerciseService) {

    @PostMapping
    fun assignExerciseToRoutine(@RequestBody dto: RoutineExerciseRequestDTO): ResponseEntity<RotuineExerciseResponseDTO> {
        val assignedExercise = routineExerciseService.assignExerciseToRoutine(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(assignedExercise)
    }

    @GetMapping("/{routineId}/exercises")
    fun getExercisesByRoutine(@PathVariable routineId: Long): ResponseEntity<List<Long?>> {
        val exerciseIds = routineExerciseService.getAllExcersiceByRoutine(routineId)
        return ResponseEntity.ok(exerciseIds)
    }

    @DeleteMapping("/{id}")
    fun deleteRoutineExercise(@PathVariable id: Long): ResponseEntity<Void> {
        routineExerciseService.deleteRoutineExercise(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/routine/{routineId}/exercise/{exerciseId}")
    fun deleteExerciseFromRoutine(@PathVariable routineId: Long, @PathVariable exerciseId: Long): ResponseEntity<Void> {
        routineExerciseService.deleteExerciseFromRoutine(routineId, exerciseId)
        return ResponseEntity.noContent().build()
    }
}