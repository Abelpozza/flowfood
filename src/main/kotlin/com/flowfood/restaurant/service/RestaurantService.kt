package com.flowfood.restaurant.service

import com.flowfood.restaurant.entity.Restaurant
import com.flowfood.restaurant.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    private val restaurantRepository: RestaurantRepository
) {

    fun save(restaurant: Restaurant): Restaurant {
        return restaurantRepository.save(restaurant)
    }

    fun findAll(): List<Restaurant> {
        return restaurantRepository.findAll()
    }

    fun findById(id: Long): Restaurant {
        return restaurantRepository.findById(id)
            .orElseThrow { RuntimeException("Restaurante não encontrado") }
    }
}