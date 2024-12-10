package io.concert.booking.application.payment.dto

data class PaymentRequest(
    val userId: Long,
    val concertId: Long,
    val seatId: Long,
    val bookingId: Long,
) {

}
