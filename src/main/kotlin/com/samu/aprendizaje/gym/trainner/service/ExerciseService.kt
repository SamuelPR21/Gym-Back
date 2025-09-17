package com.samu.aprendizaje.gym.trainner.service

import com.samu.aprendizaje.gym.trainner.dto.exercise.ExerciseDtoRequest
import com.samu.aprendizaje.gym.trainner.dto.exercise.ExerciseDtoResponse
import com.samu.aprendizaje.gym.trainner.models.Exercise
import com.samu.aprendizaje.gym.trainner.repository.ExerciseRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ExerciseService(private val exerciseRepository: ExerciseRepository) {

    fun createExercise(@RequestBody dto: ExerciseDtoRequest): ExerciseDtoResponse{
        val exercise = Exercise(name = dto.name, muscleGroup = dto.muscleGroup, description = dto.description)
        return exerciseRepository.save(exercise).toResponse()
    }

    fun getExerciseById(id: Long): ExerciseDtoResponse =
            exerciseRepository.findById(id)
                .orElseThrow{ NoSuchElementException("Exercise don't found") }
                .toResponse()

    fun updateExercise(id: Long, dto: Map<String, Any>): ExerciseDtoResponse{
        val exercise = exerciseRepository.findById(id)
            .orElseThrow { NoSuchElementException("Exercise don't found") }

        val exerciseUpdate = exercise.copy(
            name = dto["name"] as? String ?: exercise.name,
            muscleGroup = dto["muscleGroup"] as? String ?: exercise.muscleGroup,
            description = dto["description"] as? String ?: exercise.description
        )
        return exerciseRepository.save(exerciseUpdate).toResponse()
    }

//    Delete an exercise by its ID if is  not in use
    fun deleteExercise(id: Long) {
        val exercise = exerciseRepository.findById(id)
            .orElseThrow { NoSuchElementException("Exercise don't found") }
        exerciseRepository.delete(exercise)
    }

    fun listExercisesByMuscleGroup(muscleGroup: String): List<ExerciseDtoResponse> =
        exerciseRepository.findByMuscleGroup(muscleGroup).map { it.toResponse() }

    fun getAllExercises(): List<ExerciseDtoResponse> =
        exerciseRepository.findAll().map { it.toResponse() }

    private fun Exercise.toResponse() = ExerciseDtoResponse(id, name, muscleGroup,  description)

}