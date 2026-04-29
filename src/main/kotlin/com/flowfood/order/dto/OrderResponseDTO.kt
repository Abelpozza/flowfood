package com.flowfood.order.entity

import java.time.LocalDateTime

data class OrderResponseDTO(
    val id: Long?,
    val customerName: String,
    val total: Double,
    val status: OrderStatus,
    val createdAt: LocalDateTime,
    val productIds: List<Long>
)