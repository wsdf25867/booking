package io.concert.booking.interfaces.dto.concert

data class BookingCreateRequest(
    val token: String,
    val concertId: Long,
    val seatId: Long
) {

}
