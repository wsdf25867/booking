package io.concert.booking.interfaces.dto.concert

import java.math.BigDecimal

data class BookingCreateRequest(

    val seatId: Long,
    val price: BigDecimal,
)
