package io.concert.booking.interfaces.api.booking

import io.concert.booking.application.booking.BookingFacade
import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.interfaces.config.TokenRequired
import io.concert.booking.interfaces.dto.concert.BookingCreateRequest
import io.concert.booking.interfaces.dto.concert.BookingResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/booking")
class BookingController(
    private val bookingFacade: BookingFacade
): BookingApiSpecification {

    @PostMapping("/bookings")
    @TokenRequired
    override fun book(
        @RequestBody request: BookingCreateRequest
    ): BookingResponse {
        val dto = bookingFacade.create(BookingCreateDto(request.seatId, request.token))
        return BookingResponse(
            dto.bookingId,
            request.concertId,
            dto.seatId,
            LocalDateTime.now(),
        )
    }
}