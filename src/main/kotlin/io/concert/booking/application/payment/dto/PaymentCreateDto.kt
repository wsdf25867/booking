package io.concert.booking.application.payment.dto

import java.time.LocalDateTime

data class PaymentCreateDto(
    val userId: Long,
    val concertId: Long,
    val seatId: Long,
    val bookingId: Long,
    val paidAt: LocalDateTime = LocalDateTime.now(),
    ) {
}
