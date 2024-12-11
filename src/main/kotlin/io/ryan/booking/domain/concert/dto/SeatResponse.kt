package io.ryan.booking.domain.concert.dto

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
