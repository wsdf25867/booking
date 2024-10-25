package io.concert.booking.application.booking.dto

import java.time.LocalDateTime

data class BookingCreateDto(
    val seatId: Long,
    val token: String,
    val bookedAt: LocalDateTime = LocalDateTime.now(),
) {

}
