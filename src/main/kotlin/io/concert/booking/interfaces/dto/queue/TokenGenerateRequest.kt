package io.concert.booking.interfaces.dto.queue


data class TokenGenerateRequest(
    val userId: Long,
    val concertId: Long
)
