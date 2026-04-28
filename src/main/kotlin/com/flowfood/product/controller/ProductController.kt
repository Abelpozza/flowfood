package com.flowfood.product.controller

import com.flowfood.product.dto.ProductRequestDTO
import com.flowfood.product.dto.ProductResponseDTO
import com.flowfood.product.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {
    @GetMapping
    fun findAll(): List<ProductResponseDTO> {
        return productService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ProductResponseDTO {
        return productService.findById(id)
    }

    @GetMapping("/paged")
    fun findAllPaged(
        @RequestParam(required = false) name: String?,
        pageable: Pageable
    ): Page<ProductResponseDTO> {
        return productService.findAllPaged(name, pageable)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid dto: ProductRequestDTO): ProductResponseDTO {
        return productService.create(dto)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: ProductRequestDTO): ProductResponseDTO {
        return productService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        productService.delete(id)
    }

    @GetMapping("/restaurant/{restaurantId}")
    fun findByRestaurant(@PathVariable restaurantId: Long): List<ProductResponseDTO> {
        return productService.findByRestaurant(restaurantId)
    }
}