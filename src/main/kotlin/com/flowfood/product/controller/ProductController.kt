package com.flowfood.product.controller

import com.flowfood.product.dto.ProductRequestDTO
import com.flowfood.product.dto.ProductResponseDTO
import com.flowfood.product.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid dto: ProductRequestDTO): ProductResponseDTO {
        return productService.create(dto)
    }

    @GetMapping("/restaurant/{restaurantId}")
    fun findByRestaurant(@PathVariable restaurantId: Long): List<ProductResponseDTO> {
        return productService.findByRestaurant(restaurantId)
    }
}