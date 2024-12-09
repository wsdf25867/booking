package io.concert.booking.application

import java.math.BigDecimal
import java.util.*

data class BookingCreateCommand(
    val uuid: UUID,
    val seatId: Long,
    val price: BigDecimal
) {

}
