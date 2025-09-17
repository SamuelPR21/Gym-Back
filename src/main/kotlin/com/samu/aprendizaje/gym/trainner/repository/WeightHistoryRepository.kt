package com.samu.aprendizaje.gym.trainner.repository

import com.samu.aprendizaje.gym.trainner.models.UserWeightHistory
import org.springframework.data.jpa.repository.JpaRepository

interface WeightHistoryRepository: JpaRepository<UserWeightHistory, Long> {
    fun findByUserId(userId: Long): List<UserWeightHistory>

}