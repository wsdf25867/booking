package io.concert.booking.interfaces.dto.concert

import java.math.BigDecimal

data class SeatResponse(
    val id: Long,
    val price: BigDecimal,
    val seatNumber: Int,
) {

}
