package com.samu.aprendizaje.gym.trainner.repository

import com.samu.aprendizaje.gym.trainner.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    //Fin username y password

    fun findByUsername(username: String): User?
    fun findByUsernameAndPassword(username: String, password: String): User?
     //Encontra email
     fun findByEmail(email: String): User?
}