package com.samu.aprendizaje.gym.trainner.dto.routines

import java.time.LocalDate
import java.time.LocalDateTime

class DtoResponse (
    val id: Long?,
    val routineName: String,
    val user: Long?,
    val description: String? = null,
    val dateCreated: LocalDate,
    val duration: Int? = null,
)