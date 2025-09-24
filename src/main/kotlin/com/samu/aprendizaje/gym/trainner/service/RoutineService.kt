package com.samu.aprendizaje.gym.trainner.service

import com.samu.aprendizaje.gym.trainner.dto.routines.DtoRequest
import com.samu.aprendizaje.gym.trainner.dto.routines.DtoResponse
import com.samu.aprendizaje.gym.trainner.models.Routine
import com.samu.aprendizaje.gym.trainner.repository.RoutineRepository
import com.samu.aprendizaje.gym.trainner.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class RoutineService(private val routineRepository: RoutineRepository, private val userRepository: UserRepository) {

    fun createRoutineByUserId(dto: DtoRequest): DtoResponse {

        // Validate user exists
        val userId = dto.user ?: throw IllegalArgumentException("User ID must be provided")
        val user = userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found with ID: $userId") }


        val routine = Routine(
            routineName = dto.routineName,
            user = user,
            description = dto.description,
            dateCreated = dto.dateCreated,
            duration = dto.duration
        )

        return routineRepository.save(routine).toResponse()
    }


    fun getRoutineById(id: Long): DtoResponse =
        routineRepository.findById(id)
            .orElseThrow{ NoSuchElementException("Routine not found with id $id") }
            .toResponse()

    fun updateRoutine(id: Long, dto: Map<String, Any>): DtoResponse {
        val routine = routineRepository.findById(id)
            .orElseThrow { NoSuchElementException("Routine not found with id $id") }


        val updateRoutine = routine.copy(

            routineName = dto["routineName"] as? String ?: routine.routineName,
            description = dto["description"] as? String ?: routine.description,
            duration = dto["duration"] as? Int ?: routine.duration
        )

        return routineRepository.save(updateRoutine).toResponse()
    }


    fun deleteRoutine(id: Long) {
        if (!routineRepository.existsById(id)) {
            throw NoSuchElementException("Routine not found with id: $id")
        }
        routineRepository.deleteById(id)
    }

    fun allRoutinesByUserId(userId: Long): List<DtoResponse> =
        routineRepository.findAllByUserIdOrderByDateCreatedDesc(userId)
            .map { it.toResponse() }

    private fun Routine.toResponse() = DtoResponse(id, routineName, user.id, description, dateCreated, duration)
}