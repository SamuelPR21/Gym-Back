package com.samu.aprendizaje.gym.trainner.dto.set

class SetRequestDTO (
    val routineExercise: Long,
    val number: Int,
    val numberRepetitions: Int,
    val weight: Double? = null,
)