package io.concert.booking.application.queue.dto

data class TokenQueueResponse(
    val queuePosition: Int,
    val queueSize: Int,
) {

}
