package com.flowfood.products.dto


data class ProductResponseDTO(
    val id: Long?,
    val name: String,
    val description: String?,
    val price: Double,
    val restaurantId: Long
)
