package com.samu.aprendizaje.gym.trainner.controllers

import com.samu.aprendizaje.gym.trainner.dto.user.UserWeightHistoryResponseDTO
import com.samu.aprendizaje.gym.trainner.dto.user.WeightRecordRequestDTO
import com.samu.aprendizaje.gym.trainner.service.UserWeightService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/weight")
class UserWeightController(private val userWeightService: UserWeightService) {

    @PostMapping("/record")
    fun recordWeight(@RequestBody dto: WeightRecordRequestDTO): ResponseEntity<UserWeightHistoryResponseDTO> {
        val date = dto.date?.let { LocalDate.parse(it.trim()) } ?: LocalDate.now()
        val result = userWeightService.addWeightRecord(dto.userId, dto.weight, date)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/history/{userId}")
    fun getHistory(@PathVariable userId: Long): ResponseEntity<List<UserWeightHistoryResponseDTO>> {
        val history = userWeightService.getWeightHistoryByUser(userId)
        return ResponseEntity.ok(history)
    }
}
