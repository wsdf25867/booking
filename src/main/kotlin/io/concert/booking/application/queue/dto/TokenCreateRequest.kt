package io.concert.booking.application.queue.dto


data class TokenCreateRequest(
    val userId: Long,
    val concertId: Long
)
