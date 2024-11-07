package io.concert.booking.interfaces.api.booking

import io.concert.booking.interfaces.config.TokenRequired
import io.concert.booking.interfaces.dto.concert.BookingCreateRequest
import io.concert.booking.interfaces.dto.concert.BookingResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import java.util.*

@Tag(name = "Booking")
interface BookingApiSpecification {


    @PostMapping("/bookings")
    @TokenRequired
    fun book(uuid: UUID, request: BookingCreateRequest): BookingResponse
}
