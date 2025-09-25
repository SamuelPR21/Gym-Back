package com.samu.aprendizaje.gym.trainner.service

import com.samu.aprendizaje.gym.trainner.dto.user.UserWeightHistoryResponseDTO
import com.samu.aprendizaje.gym.trainner.models.UserWeightHistory
import com.samu.aprendizaje.gym.trainner.repository.UserRepository
import com.samu.aprendizaje.gym.trainner.repository.WeightHistoryRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserWeightService(
    private val userRepository: UserRepository,
    private val weightHistoryRepository: WeightHistoryRepository
) {
    fun addWeightRecord(userId: Long, weight: Int, date: LocalDate): UserWeightHistoryResponseDTO {
        val user = userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }

        val weightEntry = UserWeightHistory(
            user = user,
            weight = weight.toFloat(),
            date = date
        )

        val updatedUser = user.copy(currentWeights = weight)
        userRepository.save(updatedUser)

        return weightHistoryRepository.save(weightEntry).toResponse()
    }

    fun getWeightHistoryByUser(userId: Long): List<UserWeightHistoryResponseDTO> =
        weightHistoryRepository.findByUserId(userId)
            .sortedBy { it.date }
            .map { it.toResponse() }

    private fun UserWeightHistory.toResponse() = UserWeightHistoryResponseDTO(
        userId = this.user.id ?: 0L,
        weight = this.weight.toInt(),
        date = this.date.toString()
    )
}
