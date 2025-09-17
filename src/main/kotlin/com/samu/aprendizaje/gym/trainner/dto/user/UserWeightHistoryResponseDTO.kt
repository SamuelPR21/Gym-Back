package com.samu.aprendizaje.gym.trainner.dto.user

data class UserWeightHistoryResponseDTO (

    val userId: Long,
    val weight: Int,
    val date: String? = null
)