package io.concert.booking.domain.seat

import org.springframework.stereotype.Component

@Component
class SeatService(
    private val seatRepository: SeatRepository
) {

    fun get(seatId: Long): Seat {
        val seat = seatRepository.findById(seatId)
        return requireNotNull(seat) {
            "좌석 정보가 존재하지 않습니다."
        }
    }

    fun getBookable(concertId: Long): List<Seat> =
        seatRepository.findAllByConcertIdAndStatus(concertId, SeatStatus.EMPTY)

}