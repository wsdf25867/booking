package io.ryan.booking.domain.concert.api

import io.ryan.booking.domain.concert.dto.ConcertResponse
import io.ryan.booking.domain.concert.dto.ConcertScheduleResponse
import io.ryan.booking.domain.concert.dto.ConcertWithSeatsResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDate
import java.util.*

@Tag(name = "Concert")
interface ConcertApiSpecification {

    @GetMapping("/concert-schedules")
    fun getBookable(date: LocalDate = LocalDate.now()): List<ConcertScheduleResponse>
    @GetMapping("/concerts/{concertScheduleId}/seats")
    fun getBookableSeats(date: LocalDate, token: UUID, concertScheduleId: Long): ConcertWithSeatsResponse
}
