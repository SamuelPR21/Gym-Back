package com.samu.aprendizaje.gym.trainner.models

import jakarta.persistence.*

@Entity
@Table(name = "photo_user")
data class PhotoUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "photoUrl", nullable = false)
    var photoUrl: String,

    @Column(name= "publicId", nullable = false)
    var publicId: String,

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    val user: User
)