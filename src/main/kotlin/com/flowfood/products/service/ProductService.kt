package com.flowfood.products.service

import com.flowfood.exception.ResourceNotFoundException
import com.flowfood.products.dto.ProductRequestDTO
import com.flowfood.products.dto.ProductResponseDTO
import com.flowfood.products.entity.Product
import com.flowfood.products.repository.ProductRepository
import com.flowfood.restaurant.repository.RestaurantRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val restaurantRepository: RestaurantRepository
) {

    fun create(dto: ProductRequestDTO): ProductResponseDTO {
        val restaurant = restaurantRepository.findById(dto.restaurantId!!)
            .orElseThrow {
                ResourceNotFoundException("Restaurante não encontrado com id: ${dto.restaurantId}")
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

    fun findAll(): List<ProductResponseDTO> {
        return productRepository.findAll()
            .map { toResponse(it) }
    }

    fun findById(id: Long): ProductResponseDTO {
        val product = productRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException("Produto não encontrado com id: $id")
            }

        return toResponse(product)
    }

    fun update(id: Long, dto: ProductRequestDTO): ProductResponseDTO {
        val existingProduct = findProductOrThrow(id)

        val restaurant = restaurantRepository.findById(dto.restaurantId!!)
            .orElseThrow {
                ResourceNotFoundException("Restaurante não encontrado com id: ${dto.restaurantId}")
            }

        val updatedProduct = existingProduct.copy(
            name = dto.name,
            description = dto.description,
            price = dto.price!!,
            restaurant = restaurant
        )

        val savedProduct = productRepository.save(updatedProduct)

        return toResponse(savedProduct)
    }

    fun delete(id: Long) {
        val product = findProductOrThrow(id)
        productRepository.delete(product)
    }

    private fun findProductOrThrow(id: Long): Product {
        return productRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException("Produto não encontrado com id: $id")
            }
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
    fun findAllPaged(name: String?, pageable: Pageable): Page<ProductResponseDTO> {
        return if (name.isNullOrBlank()) {
            productRepository.findAll(pageable).map { toResponse(it) }
        } else {
            productRepository.findByNameContainingIgnoreCase(name, pageable)
                .map { toResponse(it) }
        }
    }
}