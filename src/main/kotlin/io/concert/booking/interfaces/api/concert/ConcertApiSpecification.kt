package io.concert.booking.interfaces.api.concert

import io.concert.booking.interfaces.dto.concert.ConcertResponse
import io.concert.booking.interfaces.dto.concert.ConcertWithSeatsResponse
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
