package io.concert.booking.interfaces.api.booking

import io.concert.booking.interfaces.dto.concert.BookingCreateRequest
import io.concert.booking.interfaces.dto.concert.BookingResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Booking")
interface BookingApiSpecification {

    fun book(request: BookingCreateRequest): BookingResponse
}