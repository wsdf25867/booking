package io.concert.booking.application.queue.dto

import java.util.*

data class TokenQueueDto(
    val uuid: UUID,
    val queueIndex: Int,
    val queueSize: Int,
) {

}
