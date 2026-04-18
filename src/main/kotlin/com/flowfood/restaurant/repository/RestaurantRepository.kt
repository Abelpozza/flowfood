package com.flowfood.restaurant.repository

import com.flowfood.restaurant.entity.Restaurant
import org.springframework.data.jpa.repository.JpaRepository

interface RestaurantRepository : JpaRepository<Restaurant, Long>