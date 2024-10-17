package io.concert.booking.application.booking.dto

import java.time.LocalDateTime

data class BookingCreateDto(
    val seatId: Long,
    val userId: Long,
    val bookedAt: LocalDateTime = LocalDateTime.now(),
) {

}
