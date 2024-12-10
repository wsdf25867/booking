package io.ryan.booking.domain.queue.dto


data class TokenCreateRequest(
    val userId: Long,
    val concertId: Long
)
