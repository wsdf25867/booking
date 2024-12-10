package io.concert.booking.application.queue.dto

import java.util.*

data class TokenResponse(
    val userId: Long,
    val uuid: UUID,
) {

}
