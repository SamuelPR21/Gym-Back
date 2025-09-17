package com.samu.aprendizaje.gym.trainner.service

import com.samu.aprendizaje.gym.trainner.dto.set.SetRequestDTO
import com.samu.aprendizaje.gym.trainner.dto.set.SetResponseDTO
import com.samu.aprendizaje.gym.trainner.models.Set
import com.samu.aprendizaje.gym.trainner.repository.RoutineExerciseRepository
import com.samu.aprendizaje.gym.trainner.repository.SetRepository
import org.springframework.stereotype.Service


@Service
class SetService(private val setRepository: SetRepository, private val routineExerciseRepository: RoutineExerciseRepository) {

    private fun Set.toResponse() = SetResponseDTO(id, routineExercise.id!!, number, numberRepetitions, weight)

    //Asignar series a un ejercicio  en una rutinaExercise
    fun assingSetToExercise(dto: SetRequestDTO): SetResponseDTO {
        val routineExercise = routineExerciseRepository.findById(dto.routineExercise)
            .orElseThrow { NoSuchElementException("RoutineExercise not found") }


        val set = Set(
            routineExercise = routineExercise,
            number = dto.number,
            numberRepetitions = dto.numberRepetitions,
            weight = dto.weight
        )

        return setRepository.save(set).toResponse()
    }

    //Delete a set by id
    fun deleteSetById(id: Long) {
        setRepository.deleteById(id)
    }

    //Update a set by id
    fun update(id: Long, dto: Map<String, Any>): SetResponseDTO{
        val set = setRepository.findById(id)
            .orElseThrow{ NoSuchElementException("Set not found with id: $id") }
        val updatedSet = set.copy(
            number = dto["number"] as? Int ?: set.number,
            weight = (dto["weight"] as? Number)?.toDouble() ?: set.weight,
            numberRepetitions = dto["numberRepetitions"] as? Int ?: set.numberRepetitions,
        )
        return setRepository.save(updatedSet).toResponse()
    }

    fun getSetsByExerciseId(exerciseId: Long): List<SetResponseDTO> =
        setRepository.findAllByRoutineExerciseExerciseId(exerciseId).map { it.toResponse() }





}