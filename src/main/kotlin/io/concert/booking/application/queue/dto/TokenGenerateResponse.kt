package io.concert.booking.application.queue.dto

import java.util.*

data class TokenGenerateResponse(
    val token: UUID,
    val userId: Long,
    val concertId: Long,
) {

}
