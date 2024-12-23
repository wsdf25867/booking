package io.ryan.booking.domain.booking.api

import io.ryan.booking.domain.booking.application.BookingService
import io.ryan.booking.domain.queue.api.TokenPassRequired
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/v1")
class BookingController(
    private val bookingService: BookingService
) : BookingApiSpecification {
    @PostMapping("/bookings")
    @TokenPassRequired
    override fun create(
        @Valid @RequestBody request: BookingCreateRequest
    ): ResponseEntity<Unit> {

        val bookingId = bookingService.create(request.toCommand())
        return ResponseEntity
            .created(
                UriComponentsBuilder
                    .fromPath("/api/v1/bookings/$bookingId")
                    .build()
                    .toUri()
            )
            .build()
    }
}
