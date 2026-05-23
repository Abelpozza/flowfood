package com.flowfood.auth.controller

import com.flowfood.auth.dto.AuthResponseDTO
import com.flowfood.auth.dto.LoginRequestDTO
import com.flowfood.auth.dto.RegisterRequestDTO
import com.flowfood.auth.service.AuthService
import com.flowfood.restaurant.service.JwtService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val JwtService: JwtService
){
 @PostMapping("/register")
 @ResponseStatus(HttpStatus.CREATED)
 fun register(
     @RequestBody dto: RegisterRequestDTO
 ){
 authService.register(dto)
 }
    @PostMapping("/login")
    fun login(
        @RequestBody dto: LoginRequestDTO
    ): AuthResponseDTO {
        val user = authService.login(dto)
        val token = JwtService.generatedToken(user.email)
        return AuthResponseDTO(token)
    }
}