package io.concert.booking.application.seat.dto

import java.util.*

data class SeatBookableDto(
    val concertId: Long,
    val token: UUID,
) {

}
