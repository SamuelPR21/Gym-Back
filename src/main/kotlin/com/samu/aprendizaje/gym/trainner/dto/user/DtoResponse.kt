package com.samu.aprendizaje.gym.trainner.dto.user

data class DtoResponse (
    val id: Long?,
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val currentWeights: Int?,
    val goalWeights: Int?,
    val dateOfBirth: String?,
    val dateRegister: String

)