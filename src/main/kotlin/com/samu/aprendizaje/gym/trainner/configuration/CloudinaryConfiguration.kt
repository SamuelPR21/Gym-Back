package com.samu.aprendizaje.gym.trainner.configuration

import com.cloudinary.Cloudinary
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CloudinaryConfiguration {

    @Bean
    fun cloudinaryConfig(): Cloudinary {
        val cloudinary = Cloudinary("cloudinary://926828767453748:DekOvHoAn9W1ugykw-OiB-LcNLw@dkywx1ayk")
        return cloudinary
    }


}