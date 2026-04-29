package com.flowfood.product.repository

import com.flowfood.product.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findByRestaurantId(restaurantId: Long): List<Product>

    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Product>
}