package io.ryan.booking.domain.concert.dto

import io.ryan.booking.domain.concert.domain.Seat
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
