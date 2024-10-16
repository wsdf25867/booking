package io.concert.booking.application.queue.dto

import java.util.*

data class TokenSearchCond(
    val userId: Long,
    val concertId: Long,
    val token: UUID,
) {

}
