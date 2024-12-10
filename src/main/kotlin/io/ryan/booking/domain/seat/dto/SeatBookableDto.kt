package io.ryan.booking.domain.seat.dto

import java.util.*

data class SeatBookableDto(
    val concertId: Long,
    val token: UUID,
) {

}
