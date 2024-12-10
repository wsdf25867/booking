package io.ryan.booking.domain.seat.service

import io.ryan.booking.domain.seat.domain.Seat
import io.ryan.booking.domain.seat.domain.SeatRepository
import io.ryan.booking.domain.seat.domain.SeatStatus
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SeatQueryService(
    private val seatRepository: SeatRepository
) {

    fun get(seatId: Long): Seat =
        seatRepository.findByIdOrNull(seatId) ?: throw EntityNotFoundException("없는 좌석 정보 id: $seatId")

    fun getAllEmpty(concertId: Long): List<Seat> =
        seatRepository.findAllByConcertIdAndStatus(concertId, SeatStatus.EMPTY)

}