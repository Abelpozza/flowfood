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

        return RestaurantResponseDTO(
            id = savedRestaurant.id,
            name = savedRestaurant.name,
            description = savedRestaurant.description,
            deliveryFee = savedRestaurant.deliveryFee,
            open = savedRestaurant.open
        )
    }

    fun findAll(): List<RestaurantResponseDTO> {
        return restaurantRepository.findAll().map { restaurant ->
            RestaurantResponseDTO(
                id = restaurant.id,
                name = restaurant.name,
                description = restaurant.description,
                deliveryFee = restaurant.deliveryFee,
                open = restaurant.open
            )
        }
    }

    fun findById(id: Long): RestaurantResponseDTO {
        val restaurant = restaurantRepository.findById(id)
            .orElseThrow { RuntimeException("Restaurante não encontrado") }

        return RestaurantResponseDTO(
            id = restaurant.id,
            name = restaurant.name,
            description = restaurant.description,
            deliveryFee = restaurant.deliveryFee,
            open = restaurant.open
        )
    }
}