package io.concert.booking.application.booking.dto

import java.math.BigDecimal

data class BookingCreateRequest(

    val seatId: Long,
    val price: BigDecimal,
)
