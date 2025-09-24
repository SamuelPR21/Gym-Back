package com.samu.aprendizaje.gym.trainner.repository

import com.samu.aprendizaje.gym.trainner.models.PhotoUser
import org.springframework.data.jpa.repository.JpaRepository

interface PhotoUserRepository: JpaRepository<PhotoUser, Long>{

    fun findByUserId(userId: Long): PhotoUser?
    fun deleteByUserId(userId: Long)
}