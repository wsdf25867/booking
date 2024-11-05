package io.concert.booking.interfaces.dto.queue

import java.util.*

data class TokenResponse(
    val userId: Long,
    val uuid: UUID,
) {

}
