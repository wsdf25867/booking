package io.concert.booking.domain.concert

interface ConcertRepository {
    fun findById(id: Long): Concert?

    fun save(concert: Concert): Concert
}
