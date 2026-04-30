package com.flowfood.order.controller


import com.flowfood.order.entity.OrderRequestDTO
import com.flowfood.order.entity.OrderResponseDTO
import com.flowfood.order.entity.OrderService
import com.flowfood.order.entity.OrderStatus
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid dto: OrderRequestDTO): OrderResponseDTO {
        return orderService.create(dto)
    }

    @GetMapping
    fun findAll(): List<OrderResponseDTO> {
        return orderService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): OrderResponseDTO {
        return orderService.findById(id)
    }
    @PatchMapping("/{id}/status")
    fun updateStatus(
        @PathVariable id: Long,
        @RequestParam status: OrderStatus
    ): OrderResponseDTO {
        return orderService.updateStatus(id,status)
    }

}