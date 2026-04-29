package com.flowfood.order.entity

import com.flowfood.exception.ResourceNotFoundException
import com.flowfood.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) {
    fun  create(dto: OrderRequestDTO): OrderResponseDTO {
        val products = productRepository.findAllById(dto.productIds)

        if (products.size != dto.productIds.size) {
            throw ResourceNotFoundException("Um ou mais produtos não encontrados")
        }
        val total = products.sumOf {it.price}

        val order = Order(
            customerName = dto.customerName,
            total = total,
            status = OrderStatus.CREATED,
            products = products
        )
        val saved = orderRepository.save(order)
        return toResponse(saved)
    }
    fun findAll(): List<OrderResponseDTO> {
        return orderRepository.findAll().map {toResponse(it) }
    }
    fun findById(id: Long): OrderResponseDTO {
        val order = orderRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException("Pedido não encontrado com id: $id")
            }
        return toResponse(order)
    }
    private fun toResponse(order: Order): OrderResponseDTO {
        return OrderResponseDTO(
            id = order.id,
            customerName = order.customerName,
            total = order.total,
            status = order.status,
            createdAt = order.createdAt,
            productIds = order.products.map { it.id!! }
        )
    }
}