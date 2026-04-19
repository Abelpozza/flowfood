package com.flowfood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlowfoodApplication

fun main(args: Array<String>) {
	runApplication<FlowfoodApplication>(*args)
}
