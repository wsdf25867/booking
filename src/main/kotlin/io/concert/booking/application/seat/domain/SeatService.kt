package io.concert.booking.application.seat.domain

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SeatService(
    private val seatRepository: SeatRepository
) {

    fun get(seatId: Long): Seat {
        val seat = seatRepository.findByIdOrNull(seatId)
        return requireNotNull(seat) {
            "좌석 정보가 존재하지 않습니다."
        }
    }

    fun getBookable(concertId: Long): List<Seat> =
        seatRepository.findAllByConcertIdAndStatus(concertId, SeatStatus.EMPTY)

}