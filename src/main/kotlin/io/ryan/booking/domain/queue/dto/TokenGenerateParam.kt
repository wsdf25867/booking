package io.ryan.booking.domain.queue.dto

data class TokenGenerateParam(
    val userId: Long,
    val concertId: Long) {

}
