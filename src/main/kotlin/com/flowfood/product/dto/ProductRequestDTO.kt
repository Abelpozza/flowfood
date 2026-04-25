package com.flowfood.product.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class ProductRequestDTO(

    @field:NotBlank(message = "O nome do produto é obrigatório!")
    val name: String,

    val description: String?,

    @field:NotNull(message = "O preço é obrigatório!")
    @field:DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero!")
    val price: Double?,

    @field:NotNull(message = "O restaurante é obrigatório!")
    val restaurantID: Long?
)