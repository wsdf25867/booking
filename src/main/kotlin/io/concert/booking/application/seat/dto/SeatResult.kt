package io.concert.booking.application.seat.dto

import io.concert.booking.domain.seat.Seat
import java.math.BigDecimal

data class SeatResult(
    val id: Long,
    val price: BigDecimal,
    val seatNumber: Int,
) {
    companion object {
        fun from(seat: Seat): SeatResult {
            return SeatResult(
                seat.id,
                seat.price,
                seat.seatNumber,
            )
        }
    }

}
