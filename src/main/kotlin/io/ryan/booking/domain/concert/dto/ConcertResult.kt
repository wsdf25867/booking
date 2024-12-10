package io.ryan.booking.domain.concert.dto

import io.ryan.booking.domain.concert.domain.Concert
import java.time.LocalDateTime

data class ConcertResult(
    val name: String,
    val date: LocalDateTime,
    val id: Long,
) {

    companion object {

        fun from(concert: Concert): ConcertResult = ConcertResult(
            concert.name,
            concert.date,
            concert.id,
        )
    }
}
