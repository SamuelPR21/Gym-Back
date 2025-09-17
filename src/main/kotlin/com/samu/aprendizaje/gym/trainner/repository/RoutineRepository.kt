package com.samu.aprendizaje.gym.trainner.repository

import com.samu.aprendizaje.gym.trainner.models.Routine
import org.springframework.data.jpa.repository.JpaRepository

interface RoutineRepository: JpaRepository<Routine, Long> {
    fun findAllByUserIdOrderByDateCreatedDesc(userId: Long): List<Routine>
}