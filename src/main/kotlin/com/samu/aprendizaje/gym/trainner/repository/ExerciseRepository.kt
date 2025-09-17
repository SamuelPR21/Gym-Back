package com.samu.aprendizaje.gym.trainner.repository

import com.samu.aprendizaje.gym.trainner.models.Exercise
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository: JpaRepository<Exercise, Long> {

    fun findByMuscleGroup(muscleGroup: String): List<Exercise>

}