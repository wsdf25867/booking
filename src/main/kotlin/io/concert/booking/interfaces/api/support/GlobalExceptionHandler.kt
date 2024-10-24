package io.concert.booking.interfaces.api.support

import io.concert.booking.interfaces.dto.support.HttpApiErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<HttpApiErrorResponse> {
        log.info("handleIllegalArgumentException: ", e)
        return ResponseEntity.badRequest()
            .body(HttpApiErrorResponse(e.message ?: ""))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<HttpApiErrorResponse> {
        log.info("handleIllegalArgumentException: ", e)
        return ResponseEntity.unprocessableEntity()
            .body(HttpApiErrorResponse(e.message ?: ""))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<HttpApiErrorResponse> {
        log.error("handleException: ", e)
        return ResponseEntity.internalServerError()
            .body(HttpApiErrorResponse("Internal Server Error"))
    }


    companion object {
        private val log = LoggerFactory.getLogger(RestControllerAdvice::class.java)
    }
}
