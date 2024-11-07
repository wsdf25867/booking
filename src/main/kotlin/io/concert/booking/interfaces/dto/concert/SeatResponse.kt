package io.concert.booking.interfaces.dto.concert

import io.concert.booking.application.seat.dto.SeatResult
import java.math.BigDecimal

data class SeatResponse(
    val id: Long,
    val price: BigDecimal,
    val seatNumber: Int,
) {
    companion object {
        fun from(seat: SeatResult): SeatResponse {
            return SeatResponse(
                seat.id,
                seat.price,
                seat.seatNumber
            )
        }
    }

}
