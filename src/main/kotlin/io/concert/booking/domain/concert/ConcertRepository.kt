package io.concert.booking.domain.concert

import java.time.LocalDateTime

interface ConcertRepository {
    fun findById(id: Long): Concert?

    fun save(concert: Concert): Concert

    fun findAllByDateGreaterThan(dateTime: LocalDateTime): List<Concert>
}
