package io.concert.booking.application.seat.dto

import io.concert.booking.domain.seat.Seat
import java.math.BigDecimal

data class SeatDto(
    val id: Long,
    val price: BigDecimal,
    val seatNumber: Int,
) {
    companion object {
        fun from(seat: Seat): SeatDto {
            return SeatDto(
                seat.id,
                seat.price,
                seat.seatNumber,
            )
        }
    }

}
