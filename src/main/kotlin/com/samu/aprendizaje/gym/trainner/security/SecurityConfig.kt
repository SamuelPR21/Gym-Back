package com.samu.aprendizaje.gym.trainner.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtFilter: JwtFilter) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.POST,"/users/**").permitAll()
                    .requestMatchers(HttpMethod.GET,"/users/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE,"/users/**").permitAll()
                    .requestMatchers(HttpMethod.PATCH,"/users/**").permitAll()


                    .requestMatchers(HttpMethod.POST,"/routine/**").permitAll()
                    .requestMatchers(HttpMethod.GET,"/routine/**").permitAll()
                    .requestMatchers(HttpMethod.PATCH,"/routine/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE,"/routine/**").permitAll()

                    .requestMatchers(HttpMethod.GET,"/exercise/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/exercise/**").permitAll()
                    .requestMatchers(HttpMethod.PATCH,"/exercise/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE,"/exercise/**").permitAll()

                    .requestMatchers(HttpMethod.GET,"/routine-exercises/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/routine-exercises/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE,"/routine-exercises/**").permitAll()

                    .requestMatchers(HttpMethod.GET,"/sets/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/sets/**").permitAll()
                    .requestMatchers(HttpMethod.PATCH,"/sets/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE,"/sets/**").permitAll()

                    .requestMatchers(HttpMethod.GET,"/weight/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/weight/**").permitAll()

                    .requestMatchers(HttpMethod.POST, "/photo/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/photo/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/photo/**").permitAll()





                    .anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }
}