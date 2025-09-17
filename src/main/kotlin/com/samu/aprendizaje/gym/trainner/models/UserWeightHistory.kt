package com.samu.aprendizaje.gym.trainner.models

import jakarta.persistence.Entity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "user_weight_history")
data class UserWeightHistory (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    val user: User,

    val weight: Float,

    val date: LocalDate = LocalDate.now()
)