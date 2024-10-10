package io.concert.booking.interfaces.dto.payment

data class PaymentRequest(
    val userId: Long,
    val concertId: Long,
    val seatId: Long,
    val bookingId: Long,
) {

}
