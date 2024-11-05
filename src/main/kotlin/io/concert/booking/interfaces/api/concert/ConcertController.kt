package io.concert.booking.interfaces.api.concert

import io.concert.booking.application.booking.BookingApplicationService
import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.application.concert.ConcertApplicationService
import io.concert.booking.application.concert.dto.ConcertSearchDto
import io.concert.booking.application.seat.SeatApplicationService
import io.concert.booking.application.seat.dto.SeatBookableDto
import io.concert.booking.interfaces.dto.concert.BookingCreateRequest
import io.concert.booking.interfaces.dto.concert.BookingResponse
import io.concert.booking.interfaces.dto.concert.ConcertResponse
import io.concert.booking.interfaces.dto.concert.SeatResponse
import io.concert.booking.interfaces.dto.concert.SimpleConcertResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/api/v1")
class ConcertController(
    private val concertApplicationService: ConcertApplicationService,
    private val seatApplicationService: SeatApplicationService,
    private val bookingApplicationService: BookingApplicationService,
) : ConcertApiSpecification {

    @GetMapping("/concerts")
    override fun findAll(@RequestParam("date") date: LocalDate): List<SimpleConcertResponse> {
        return concertApplicationService.findAllBookable(ConcertSearchDto(date.atStartOfDay()))
            .map {
                SimpleConcertResponse(
                    it.id,
                    it.name,
                    it.date
                )
            }
    }

    @GetMapping("/concerts/{concertId}/seats")
    override fun findSeats(
        @RequestParam date: LocalDate,
        @RequestParam("token") token: UUID,
        @PathVariable concertId: Long,
    ): ConcertResponse {
        val concert = concertApplicationService.findAllBookable(ConcertSearchDto(date.atStartOfDay()))
            .find { it.id == concertId }
            ?: throw IllegalArgumentException()
        val seats = seatApplicationService.findBookable(SeatBookableDto(concertId, token))
        return ConcertResponse(
            concert.id,
            concert.name,
            concert.date,
            seats.map {
                SeatResponse(
                    it.id,
                    it.price,
                    it.seatNumber,
                )
            }
        )
    }

    @PostMapping("/bookings")
    fun book(
        @RequestBody request: BookingCreateRequest
    ): BookingResponse {
        val dto = bookingApplicationService.create(BookingCreateDto(request.seatId, request.token))
        return BookingResponse(
            dto.bookingId,
            request.concertId,
            dto.seatId,
            LocalDateTime.now(),
        )
    }
}
