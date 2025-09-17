package com.samu.aprendizaje.gym.trainner.models

import jakarta.persistence.*

@Entity
@Table(name = "Exercise")
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "muscleGroup", nullable = false)
    val muscleGroup: String,

    @Column(name = "description")
    val description: String? = null,

    @OneToMany(mappedBy = "exercise", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val routineExercise: List<RoutineExercise> = emptyList(),

)