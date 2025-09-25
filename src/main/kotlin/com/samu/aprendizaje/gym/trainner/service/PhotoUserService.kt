package com.samu.aprendizaje.gym.trainner.service

import com.cloudinary.Cloudinary
import com.samu.aprendizaje.gym.trainner.models.PhotoUser
import com.samu.aprendizaje.gym.trainner.repository.PhotoUserRepository
import com.samu.aprendizaje.gym.trainner.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class PhotoUserService (private val photoUserRepository: PhotoUserRepository, private  val cloudinary: Cloudinary, private val userRepository: UserRepository){

    @Transactional
    fun uploadPhoto(userId: Long, file: MultipartFile): String {

        val user = userRepository.findById(userId)
            .orElseThrow { RuntimeException("Usuario no encontrado") }

        val existingPhoto = photoUserRepository.findByUserId(userId)
        existingPhoto?.let {
            deleteFromCloudinary(it.publicId)
        }

        val uploadResult = cloudinary.uploader().upload(
            file.bytes,
            mapOf("folder" to "users/$userId")
        )

        val imageUrl = uploadResult["secure_url"] as String
        val publicId = uploadResult["public_id"] as String

        val photoUser = existingPhoto?.apply {
            this.photoUrl = imageUrl
            this.publicId = publicId
        }?: PhotoUser(
            photoUrl = imageUrl,
            publicId = publicId,
            user = user
        )

        photoUserRepository.save(photoUser)

        return imageUrl
    }

    fun deleteFromCloudinary(publicId: String) {
        cloudinary.uploader().destroy(publicId, emptyMap<String, Any>())
    }

    @Transactional
    fun deletePhoto(userId: Long) {
        val existingPhoto = photoUserRepository.findByUserId(userId)
        if (existingPhoto != null) {
            deleteFromCloudinary(existingPhoto.publicId)
            photoUserRepository.deleteByUserId(userId)
        } else {
            throw RuntimeException("No se encontró una foto para el usuario con ID: $userId")
        }
    }

    fun getPhotoByUserId(userId: Long): PhotoUser? {
        return photoUserRepository.findByUserId(userId)
    }
}