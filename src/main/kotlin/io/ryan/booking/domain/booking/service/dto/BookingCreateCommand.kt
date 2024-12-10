package io.ryan.booking.domain.booking.service.dto

import java.math.BigDecimal
import java.util.*

data class BookingCreateCommand(
    val uuid: UUID,
    val seatId: Long,
    val price: BigDecimal
) {

}
