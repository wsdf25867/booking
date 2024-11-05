package io.concert.booking.interfaces.api.booking

import io.concert.booking.application.booking.BookingFacade
import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.interfaces.config.TokenRequired
import io.concert.booking.interfaces.dto.concert.BookingCreateRequest
import io.concert.booking.interfaces.dto.concert.BookingResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.UUID

@RestController
@RequestMapping("/api/v1")
class BookingController(
    private val bookingFacade: BookingFacade
): BookingApiSpecification {

    @PostMapping("/bookings")
    @TokenRequired
    override fun book(
        @RequestHeader("Authorization") uuid: UUID,
        @RequestBody request: BookingCreateRequest
    ): BookingResponse {
        val result = bookingFacade.bookSeat(seatId = request.seatId, uuid = uuid)
        return BookingResponse.from(result)
    }
}
