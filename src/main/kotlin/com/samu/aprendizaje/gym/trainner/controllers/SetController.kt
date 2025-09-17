package com.samu.aprendizaje.gym.trainner.controllers

import com.samu.aprendizaje.gym.trainner.dto.set.SetRequestDTO
import com.samu.aprendizaje.gym.trainner.dto.set.SetResponseDTO
import com.samu.aprendizaje.gym.trainner.models.Set
import com.samu.aprendizaje.gym.trainner.service.SetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/sets")
class SetController (private val setService: SetService) {

    @PostMapping("/create")
    fun assignSetToExercise(@RequestBody set: SetRequestDTO): SetResponseDTO {
        return setService.assingSetToExercise(set)
    }

    @DeleteMapping("/{id}")
    fun deleteSetById(@PathVariable id: Long): ResponseEntity<Void> {
        setService.deleteSetById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun updateSet(@PathVariable id: Long, @RequestBody fields: Map<String, Any>): ResponseEntity<SetResponseDTO> =
        ResponseEntity.ok(setService.update(id, fields))


    @GetMapping("/exercise/{exerciseId}")
    fun getSetsByExerciseId(@PathVariable exerciseId: Long): ResponseEntity<List<SetResponseDTO>> =
        ResponseEntity.ok(setService.getSetsByExerciseId(exerciseId))


}