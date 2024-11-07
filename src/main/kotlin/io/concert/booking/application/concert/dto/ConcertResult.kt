package io.concert.booking.application.concert.dto

import io.concert.booking.domain.concert.Concert
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
