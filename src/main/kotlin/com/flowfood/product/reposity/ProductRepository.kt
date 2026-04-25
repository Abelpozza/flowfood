package com.flowfood.product.reposity

import com.flowfood.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findByRestaurantId(restaurantId: Long): List<Product>
}