package com.flowfood.restaurant.controller

import com.flowfood.restaurant.entity.Restaurant
import com.flowfood.restaurant.service.RestaurantService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restaurants")
class RestaurantController(
    private val restaurantService: RestaurantService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody restaurant: Restaurant): Restaurant {
        return restaurantService.save(restaurant)
    }

    @GetMapping
    fun findAll(): List<Restaurant> {
        return restaurantService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Restaurant {
        return restaurantService.findById(id)
    }
}