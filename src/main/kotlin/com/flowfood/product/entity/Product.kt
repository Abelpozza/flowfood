package com.flowfood.product.entity

import com.flowfood.restaurant.entity.Restaurant
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val description: String?,
    val price: Double,

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    val restaurant: Restaurant
)