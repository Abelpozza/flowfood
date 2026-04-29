package com.flowfood.order.entity

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class OrderRequestDTO(
    @field:NotBlank(message = "O nome do cliente é obrigatório!")
    val customerName: String,

    @field:NotEmpty(message = "O pedido precisa ter pelo menos um produto!")
    val productIds: List<Long>
)