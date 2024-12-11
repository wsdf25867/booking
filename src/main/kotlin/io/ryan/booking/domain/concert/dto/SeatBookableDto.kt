package io.ryan.booking.domain.concert.dto

import java.util.*

data class SeatBookableDto(
    val concertId: Long,
    val token: UUID,
) {

}
