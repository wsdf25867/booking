package io.concert.booking.application.booking.api

import io.concert.booking.application.booking.dto.BookingCreateRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import java.util.*

@Tag(name = "Booking")
interface BookingApiSpecification {

    fun book(uuid: UUID, request: BookingCreateRequest): ResponseEntity<*>
}
