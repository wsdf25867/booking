package io.concert.booking.interfaces.dto.queue


data class TokenCreateRequest(
    val userId: Long,
    val concertId: Long
)
