package com.flowfood.auth.service

import com.flowfood.auth.dto.LoginRequestDTO
import com.flowfood.auth.dto.RegisterRequestDTO
import com.flowfood.user.entity.Role
import com.flowfood.user.entity.User
import com.flowfood.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
){
    fun register(dto: RegisterRequestDTO) {
        val user = User(
            email = dto.email,
            password = passwordEncoder.encode(dto.password),
            role = Role.USER
            )
        userRepository.save(user)
    }
    fun login(dto: LoginRequestDTO): User {
        val user = userRepository.findByEmail(dto.email)
            ?: throw RuntimeException("Usuário não encontrado")

        val passwordMatches = passwordEncoder.matches(
            dto.password,
            user.password
        )
        if (!passwordMatches) {
            throw RuntimeException("Senha inválida")
        }
        return user
    }
}