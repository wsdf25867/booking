package io.concert.booking.interfaces.api.concert

import io.concert.booking.interfaces.dto.concert.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1")
class ConcertController {

    @GetMapping("/concerts")
    fun findAll(@RequestParam("date") date: LocalDate = LocalDate.now()): List<SimpleConcertResponse> {
        return listOf(SimpleConcertResponse(1, "some-name", LocalDateTime.now()))
    }

    @GetMapping("/concerts/{concertId}/seats")
    fun findSeats(
        @RequestParam date: LocalDate = LocalDate.now(),
        @PathVariable concertId: Long,
    ): ConcertResponse {
        return ConcertResponse(
            concertId,
            "some-name",
            LocalDateTime.now(),
            listOf(
                SeatResponse(1, BigDecimal(80), 1),
                SeatResponse(2, BigDecimal(120), 2),
            )
        )
    }

    @PostMapping("/bookings")
    fun book(
        @RequestBody request: BookingCreateRequest
    ): BookingResponse {

        return BookingResponse(
            request.concertId,
            request.concertId,
            request.seatId,
            LocalDateTime.now(),
        )
    }
}
