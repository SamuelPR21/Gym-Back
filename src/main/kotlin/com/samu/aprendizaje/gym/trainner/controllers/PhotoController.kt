package com.samu.aprendizaje.gym.trainner.controllers



import com.samu.aprendizaje.gym.trainner.service.PhotoUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/photo")
class PhotoController(private val photoUserService: PhotoUserService) {

    @PostMapping("/upload/{userId}")
    fun uploadPhoto(@PathVariable userId: Long, @RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        val imageUrl = photoUserService.uploadPhoto(userId, file)
        return ResponseEntity.status(HttpStatus.CREATED).body(imageUrl)
    }

    @DeleteMapping("/delete/{userId}")
    fun deletePhoto(@PathVariable userId: Long): ResponseEntity<Void> {
        photoUserService.deletePhoto(userId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{userId}")
    fun getPhotoByUserId(@PathVariable userId: Long): ResponseEntity<String>{
        val photo = photoUserService.getPhotoByUserId(userId)
        return if (photo != null) {
            ResponseEntity.ok(photo.photoUrl)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}