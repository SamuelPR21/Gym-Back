package com.samu.aprendizaje.gym.trainner.models

import jakarta.persistence.*

@Entity
@Table(name = "sets")
data class Set(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "routine_exercise_id", nullable = false)
    val routineExercise: RoutineExercise,

    @Column(name = "number", nullable = false)
    val number: Int,

    @Column(name = "number_repetitions", nullable = false)
    val numberRepetitions: Int,

    @Column(name = "weight", nullable = true)
    val weight: Double? = null
)