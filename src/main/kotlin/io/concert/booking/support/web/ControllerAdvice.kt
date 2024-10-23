package io.concert.booking.support.web

import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class RestControllerAdvice: ResponseEntityExceptionHandler() {
}
