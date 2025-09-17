package com.samu.aprendizaje.gym.trainner.models

import jakarta.persistence.*


@Entity
@Table(name = "routine_exercises")
data class RoutineExercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "notes", nullable = false)
    val notes: String? = null,


    @ManyToOne
    @JoinColumn(name ="rutinaId", nullable = false)
    val routine: Routine,

    @ManyToOne
    @JoinColumn(name = "exerciseId", nullable = false)
    val exercise: Exercise,

    @OneToMany(mappedBy = "routineExercise", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val sets: List<Set> = emptyList()

)