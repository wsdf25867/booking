package io.concert.booking.interfaces.api.concert

import io.concert.booking.interfaces.dto.concert.ConcertResponse
import io.concert.booking.interfaces.dto.concert.SimpleConcertResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDate
import java.util.*

@Tag(name = "Concert")
interface ConcertApiSpecification {

    @GetMapping("/concerts")
    fun findAll(date: LocalDate = LocalDate.now()): List<SimpleConcertResponse>
    @GetMapping("/concerts/{concertId}/seats")
    fun findSeats(date: LocalDate, token: UUID, concertId: Long): ConcertResponse
}
