package io.ryan.booking.domain.booking.api

import io.ryan.booking.domain.booking.dto.BookingCreateRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import java.util.*

@Tag(name = "Booking")
interface BookingApiSpecification {

    fun book(uuid: UUID, request: BookingCreateRequest): ResponseEntity<*>
}
