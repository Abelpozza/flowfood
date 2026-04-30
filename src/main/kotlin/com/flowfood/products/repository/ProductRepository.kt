package com.flowfood.products.repository

import com.flowfood.products.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findByRestaurantId(restaurantId: Long): List<Product>

    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Product>
}