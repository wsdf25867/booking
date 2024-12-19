package io.ryan.booking.domain.booking.api

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "Booking")
interface BookingApiSpecification {

    fun create(request: BookingCreateRequest): ResponseEntity<*>
}
