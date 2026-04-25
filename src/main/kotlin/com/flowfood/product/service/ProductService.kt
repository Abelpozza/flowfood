package com.flowfood.product.service

import com.flowfood.exception.ResourceNotFoundException
import com.flowfood.product.dto.ProductRequestDTO
import com.flowfood.product.dto.ProductResponseDTO
import com.flowfood.product.entity.Product
import com.flowfood.product.reposity.ProductRepository
import com.flowfood.restaurant.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service

class ProductService(
private val productRepository: ProductRepository,
        private val  restaurantRepository: RestaurantRepository) {

    fun create(dto: ProductRequestDTO): ProductResponseDTO {
        val restaurant = restaurantRepository.findById(dto.restaurantID!!)
            .orElseThrow {
                ResourceNotFoundException("Restaurante não encontrado com id: ${dto.restaurantID}")
            }

        val product = Product(
            name = dto.name,
            description = dto.description,
            price = dto.price!!,
            restaurant = restaurant
        )
        val savedProduct = productRepository.save(product)

        return toResponse(savedProduct)
    }


    fun findByRestaurant(restaurantId: Long): List<ProductResponseDTO> {
        return productRepository.findByRestaurantId(restaurantId)
            .map { toResponse(it) }
    }

    private fun toResponse(product: Product): ProductResponseDTO {
        return ProductResponseDTO(
            id = product.id,
            name = product.name,
            description = product.description,
            price = product.price,
            restaurantId = product.restaurant.id!!
        )
    }
}