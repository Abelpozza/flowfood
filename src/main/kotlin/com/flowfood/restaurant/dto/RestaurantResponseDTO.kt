package com.flowfood.restaurant.dto

data class RestaurantResponseDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val deliveryFee: Double?,
    val open: Boolean?
)