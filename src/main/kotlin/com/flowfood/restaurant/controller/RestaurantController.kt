package com.flowfood.restaurant.controller

import com.flowfood.restaurant.dto.RestaurantRequestDTO
import com.flowfood.restaurant.dto.RestaurantResponseDTO
import com.flowfood.restaurant.service.RestaurantService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restaurants")
class RestaurantController(
    private val restaurantService: RestaurantService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid dto: RestaurantRequestDTO): RestaurantResponseDTO {
        return restaurantService.create(dto)
    }

    @GetMapping
    fun findAll(): List<RestaurantResponseDTO> {
        return restaurantService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): RestaurantResponseDTO {
        return restaurantService.findById(id)
    }
}