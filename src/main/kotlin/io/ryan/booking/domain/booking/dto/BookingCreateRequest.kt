package io.ryan.booking.domain.booking.dto

import java.math.BigDecimal

data class BookingCreateRequest(
    val userId: Long,
    val concertScheduleId: Long,
    val seatId: Long,
    val price: BigDecimal,
)
