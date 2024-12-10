package io.ryan.booking.domain.queue.dto

import java.util.*

data class TokenDto(
    val uuid: UUID,
    val userId: Long,
    val concertId: Long,
) {

}
