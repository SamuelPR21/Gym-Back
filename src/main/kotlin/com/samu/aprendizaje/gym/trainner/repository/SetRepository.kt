package com.samu.aprendizaje.gym.trainner.repository

import com.samu.aprendizaje.gym.trainner.models.RoutineExercise
import com.samu.aprendizaje.gym.trainner.models.Set
import org.springframework.data.jpa.repository.JpaRepository

interface SetRepository: JpaRepository<Set, Long> {
    //List all sets by routineExcesi id
    fun findAllByRoutineExerciseId(routineExerciseId: RoutineExercise): List<Set>

    fun findAllByRoutineExerciseExerciseId(exerciseId: Long): List<Set>

}