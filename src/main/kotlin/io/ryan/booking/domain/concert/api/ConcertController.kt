package io.ryan.booking.domain.concert.api

import io.ryan.booking.domain.concert.service.ConcertFacade
import io.ryan.booking.domain.seat.dto.ConcertResponse
import io.ryan.booking.domain.seat.dto.ConcertWithSeatsResponse
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
    private val concertFacade: ConcertFacade,
) : ConcertApiSpecification {

    @GetMapping("/concerts")
    override fun getBookable(@RequestParam("date") date: LocalDate): List<ConcertResponse> {
        return concertFacade.getBookable(date.atStartOfDay())
            .map { ConcertResponse.from(it) }
    }

    @GetMapping("/concerts/{concertId}/seats")
    override fun getBookableSeats(
        @RequestParam date: LocalDate,
        @RequestParam("token") token: UUID,
        @PathVariable concertId: Long,
    ): ConcertWithSeatsResponse {
        return concertFacade.getBookableWithSeats(concertId)
            .let { ConcertWithSeatsResponse.from(it) }
    }


}
