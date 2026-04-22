package com.flowfood.restaurant.service

import com.flowfood.restaurant.dto.RestaurantRequestDTO
import com.flowfood.restaurant.dto.RestaurantResponseDTO
import com.flowfood.restaurant.entity.Restaurant
import com.flowfood.restaurant.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    private val restaurantRepository: RestaurantRepository
) {

    fun create(dto: RestaurantRequestDTO): RestaurantResponseDTO {
        val restaurant = Restaurant(
            name = dto.name,
            description = dto.description,
            deliveryFee = dto.deliveryFee!!,
            open = dto.open!!
        )

        val savedRestaurant = restaurantRepository.save(restaurant)
        return toResponse(savedRestaurant)
    }

    fun findAll(): List<RestaurantResponseDTO> {
        return restaurantRepository.findAll().map { toResponse(it) }
    }

    fun findById(id: Long): RestaurantResponseDTO {
        val restaurant = restaurantRepository.findById(id)
            .orElseThrow { RuntimeException("Restaurante não encontrado") }

        return toResponse(restaurant)
    }

    fun update(id: Long, dto: RestaurantRequestDTO): RestaurantResponseDTO {
        val restaurant = restaurantRepository.findById(id)
            .orElseThrow { RuntimeException("Restaurante não encontrado") }

        val updatedRestaurant = restaurant.copy(
            name = dto.name,
            description = dto.description,
            deliveryFee = dto.deliveryFee!!,
            open = dto.open!!
        )

        val savedRestaurant = restaurantRepository.save(updatedRestaurant)
        return toResponse(savedRestaurant)
    }

    fun delete(id: Long) {
        val restaurant = restaurantRepository.findById(id)
            .orElseThrow { RuntimeException("Restaurante não encontrado") }

        restaurantRepository.delete(restaurant)
    }

    private fun toResponse(restaurant: Restaurant): RestaurantResponseDTO {
        return RestaurantResponseDTO(
            id = restaurant.id,
            name = restaurant.name,
            description = restaurant.description,
            deliveryFee = restaurant.deliveryFee,
            open = restaurant.open
        )
    }
}