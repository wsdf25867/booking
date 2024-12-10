package io.concert.booking.application.concert.api

import io.concert.booking.application.seat.dto.ConcertResponse
import io.concert.booking.application.seat.dto.ConcertWithSeatsResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDate
import java.util.*

@Tag(name = "Concert")
interface ConcertApiSpecification {

    @GetMapping("/concerts")
    fun getBookable(date: LocalDate = LocalDate.now()): List<ConcertResponse>
    @GetMapping("/concerts/{concertId}/seats")
    fun getBookableSeats(date: LocalDate, token: UUID, concertId: Long): ConcertWithSeatsResponse
}