package com.flowfood.order.entity

import com.flowfood.product.entity.Product
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val customerName: String,
    val total: Double,

    @Enumerated(EnumType.STRING)
    val status: OrderStatus = OrderStatus.CREATED,
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    val products: List<Product> = emptyList()
)