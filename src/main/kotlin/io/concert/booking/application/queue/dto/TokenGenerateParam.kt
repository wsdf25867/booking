package io.concert.booking.application.queue.dto

data class TokenGenerateParam(
    val userId: Long,
    val concertId: Long) {

}
