package io.ryan.booking.domain.support.api

import jakarta.persistence.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

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

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<HttpApiErrorResponse> {
        log.info("handleEntityNotFoundException: ", e)
        return ResponseEntity.unprocessableEntity()
            .body(HttpApiErrorResponse(e.message ?: ""))
    }

    companion object {
        private val log = LoggerFactory.getLogger(RestControllerAdvice::class.java)
    }
}
