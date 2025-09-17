package com.samu.aprendizaje.gym.trainner.repository

import com.samu.aprendizaje.gym.trainner.models.RoutineExercise
import org.springframework.data.jpa.repository.JpaRepository

interface RoutineExerciseRepository : JpaRepository<RoutineExercise, Long> {

    fun findByRoutineIdAndExerciseId(routineId: Long, exerciseId: Long): RoutineExercise?

    fun findAllByRoutineId(routineId: Long): List<RoutineExercise>


}