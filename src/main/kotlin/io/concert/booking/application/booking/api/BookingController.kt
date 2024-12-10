package io.concert.booking.application.booking.api

import io.concert.booking.application.booking.service.dto.BookingCreateCommand
import io.concert.booking.application.booking.service.BookingService
import io.concert.booking.application.queue.api.TokenPassRequired
import io.concert.booking.application.booking.dto.BookingCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/api/v1")
class BookingController(
    private val bookingService: BookingService
) : BookingApiSpecification {

    @PostMapping("/bookings")
    @TokenPassRequired
    override fun book(
        @RequestHeader("Authorization") uuid: UUID,
        @RequestBody request: BookingCreateRequest
    ): ResponseEntity<*> {
        val bookingId = bookingService.create(BookingCreateCommand(uuid, request.seatId, request.price))
        return ResponseEntity
            .created(
                UriComponentsBuilder
                    .fromPath("/$bookingId")
                    .build()
                    .toUri()
            )
            .build<Any>()
    }
}
