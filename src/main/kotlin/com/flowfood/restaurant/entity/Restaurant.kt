package com.flowfood.restaurant.entity

import com.flowfood.products.entity.Product
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "restaurants")
data class Restaurant(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    val description: String? = null,

    @Column(nullable = false)
    val deliveryFee: Double,

    @Column(nullable = false)
    val open: Boolean = true
)

@OneToMany(mappedBy = "restaurant")
val products: List<Product> = emptyList()

