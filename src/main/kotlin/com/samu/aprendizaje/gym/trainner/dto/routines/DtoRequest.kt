package com.samu.aprendizaje.gym.trainner.dto.routines

import java.time.LocalDate

class DtoRequest (
    val routineName: String,
    val user: Long?,
    val description: String? = null,
    val dateCreated: LocalDate,
    val duration: Int? = null,
)