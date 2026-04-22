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
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid dto: RestaurantRequestDTO): RestaurantResponseDTO {
        return restaurantService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        restaurantService.delete(id)
    }
}