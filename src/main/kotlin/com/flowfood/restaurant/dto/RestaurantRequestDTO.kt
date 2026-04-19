package com.flowfood.restaurant.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class  RestaurantRequestDTO(

    @field:NotBlank(message = "O nome do Restaurante é obrigatório!")
    val name: String,

    val description: String?,

    @field:NotNull(message = "A taxa de entrega é obrigatória!")
    @field:DecimalMin(value = "0.0", inclusive = true, message = "A taxa de entrega não pode ser negativa!")
    val deliveryFee: Double,

    @field:NotNull(message = "O status de aberto/fechado é obrigatório!")
    val open: Boolean
)