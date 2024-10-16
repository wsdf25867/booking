package io.concert.booking.application.queue.dto

import java.util.UUID

data class TokenSearchResponse(
    val token: UUID,
    val queueIndex: Int,
    val queueSize: Int,
) {

}
