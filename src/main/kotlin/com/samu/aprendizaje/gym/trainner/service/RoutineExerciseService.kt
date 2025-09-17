package com.samu.aprendizaje.gym.trainner.service

import com.samu.aprendizaje.gym.trainner.dto.RoutineExercise.RotuineExerciseResponseDTO
import com.samu.aprendizaje.gym.trainner.dto.RoutineExercise.RoutineExerciseRequestDTO
import com.samu.aprendizaje.gym.trainner.models.RoutineExercise
import com.samu.aprendizaje.gym.trainner.repository.ExerciseRepository
import com.samu.aprendizaje.gym.trainner.repository.RoutineExerciseRepository
import com.samu.aprendizaje.gym.trainner.repository.RoutineRepository
import com.samu.aprendizaje.gym.trainner.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class RoutineExerciseService(
    private val routineExerciseRepository: RoutineExerciseRepository,
    private val routineRepository: RoutineRepository,
    private val exerciseRepository: ExerciseRepository
) {

    private fun RoutineExercise.toResponse() = RotuineExerciseResponseDTO(id, notes, routine.id, exercise.id)

    fun assignExerciseToRoutine(dto: RoutineExerciseRequestDTO): RotuineExerciseResponseDTO {


        val exerciseId = dto.exercise ?: throw IllegalArgumentException("Exercise ID must be provided")
        val exercise = exerciseRepository.findById(exerciseId)
            .orElseThrow { NoSuchElementException("User not found with ID: $exerciseId") }

        val routineId = dto.routine ?: throw IllegalArgumentException("Exercise ID must be provided")
        val routine = routineRepository.findById(routineId)
            .orElseThrow { NoSuchElementException("User not found with ID: $routineId") }


        val routineExercise = RoutineExercise(
            routine = routine,
            exercise = exercise,
            notes = dto.notes

        )

        return routineExerciseRepository.save(routineExercise).toResponse()
    }



    fun deleteRoutineExercise(id: Long) {
        if (!routineExerciseRepository.existsById(id)) {
            throw NoSuchElementException("RoutineExercise not found with id: $id")
        }
        routineExerciseRepository.deleteById(id)
    }

    //Get all Exercise By RoutineID
    fun getAllExcersiceByRoutine(routineId: Long): List<Long?> {
        if (!routineRepository.existsById(routineId)) {
            throw NoSuchElementException("Routine not found with id: $routineId")
        }

        return routineExerciseRepository.findAllByRoutineId(routineId)
            .map { it.exercise.id }
    }

    //Delete Excercise by ID of a routineExcersice
    fun deleteExerciseFromRoutine(routineId: Long, exerciseId: Long) {
        if (!routineRepository.existsById(routineId)) {
            throw NoSuchElementException("Routine not found with id: $routineId")
        }

        val routineExercise = routineExerciseRepository.findByRoutineIdAndExerciseId(routineId, exerciseId)
            ?: throw NoSuchElementException("Exercise not found in routine with id: $routineId and exercise id: $exerciseId")

        routineExerciseRepository.delete(routineExercise)
    }




}