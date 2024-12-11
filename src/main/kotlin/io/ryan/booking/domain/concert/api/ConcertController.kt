package io.ryan.booking.domain.concert.api

import io.ryan.booking.domain.concert.dto.ConcertScheduleResponse
import io.ryan.booking.domain.concert.dto.ConcertWithSeatsResponse
import io.ryan.booking.domain.concert.application.ConcertQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api/v1")
class ConcertController(
    private val concertQueryService: ConcertQueryService,
) : ConcertApiSpecification {

    @GetMapping("/concert-schedules")
    override fun getBookable(@RequestParam("date") date: LocalDate): List<ConcertScheduleResponse> {
        TODO()
    }

    @GetMapping("/concert-schedules/{concertScheduleId}/seats")
    override fun getBookableSeats(
        @RequestParam date: LocalDate,
        @RequestParam("token") token: UUID,
        @PathVariable concertScheduleId: Long,
    ): ConcertWithSeatsResponse {
        TODO()
    }


}
