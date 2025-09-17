package com.samu.aprendizaje.gym.trainner.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "routine")
data class Routine(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "routineName", nullable = false)
    val routineName: String,

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    val user: User,

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "date_created")
    val dateCreated: LocalDate = LocalDate.now(),

    @Column (name = "duration")
    val duration: Int? = null, // Duration in minutes

    @OneToMany(mappedBy = "routine", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val routineExercise: List<RoutineExercise> = emptyList()


)
