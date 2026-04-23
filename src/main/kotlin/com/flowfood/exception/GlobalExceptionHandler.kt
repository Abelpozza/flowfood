package com.flowfood.exception

import jakarta.servlet.http.HttpServletRequest
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(
        ex: ResourceNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val error = ApiError(
            timestamp = LocalDateTime.now(),
            status = 404,
            error = "Not Found",
            message = ex.message,
            path = request.requestURI
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
            }
    @ExceptionHandler(
        MethodArgumentNotValidException::class)
        fun handleValidation(
            ex: MethodArgumentNotValidException,
            request: HttpServletRequest ):
        ResponseEntity<ApiError> {

            val message = ex.bindingResult.fieldErrors.firstOrNull()?.defaultMessage ?: "Dados Inválidos!"

            val error = ApiError(
                timestamp = LocalDateTime.now(),
                status = 400,
                error = "Bad Request",
                message = message,
                path = request.requestURI
            )

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
        }

        @ExceptionHandler(Exception::class)
        fun handleGeneric(
            ex: Exception,
            request: HttpServletRequest
        ): ResponseEntity <ApiError> {

            val error = ApiError(
                timestamp = LocalDateTime.now(),
                status = 500,
                error = "Internal Server Error",
                message = ex.message,
                path = request.requestURI
            )

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error)
        }
}



