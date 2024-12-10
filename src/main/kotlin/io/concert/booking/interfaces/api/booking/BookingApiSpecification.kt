package io.concert.booking.interfaces.api.booking

import io.concert.booking.interfaces.dto.concert.BookingCreateRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import java.util.*

@Tag(name = "Booking")
interface BookingApiSpecification {

    fun book(uuid: UUID, request: BookingCreateRequest): ResponseEntity<*>
}
