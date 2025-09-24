package com.samu.aprendizaje.gym.trainner.models
import jakarta.persistence.*

@Entity
@Table(name = "users")

data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = true)
    val name: String,

    @Column(name = "username", nullable = false, unique = true)
    val username: String,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "currentWeights", nullable = false)
    val currentWeights: Int? = null,

    @Column(name = "goalWeights", nullable = false)
    val goalWeights: Int? = null,

    @Column(name = "dateOfBirth", nullable = true)
    val dateOfBirth: String,

    @OneToMany(mappedBy = "user", cascade =  [CascadeType.ALL], fetch = FetchType.LAZY)
    val routines: List<Routine> = emptyList(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val weightHistory: List<UserWeightHistory> = mutableListOf(),

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val photo: PhotoUser? = null


)



