package io.ryan.booking.domain.queue.dto

import java.util.*

data class TokenResponse(
    val userId: Long,
    val uuid: UUID,
) {

}
