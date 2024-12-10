package io.ryan.booking.domain.queue.dto

data class TokenQueueResponse(
    val queuePosition: Int,
    val queueSize: Int,
) {

}
