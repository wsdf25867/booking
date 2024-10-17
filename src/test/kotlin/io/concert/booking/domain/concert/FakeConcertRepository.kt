package io.concert.booking.domain.concert

import java.time.LocalDateTime

class FakeConcertRepository : ConcertRepository {
    private val store: MutableMap<Long, Concert> = mutableMapOf()
    private var sequence: Long = 1

    override fun findById(id: Long): Concert? {
        return store[id]
    }

    override fun save(concert: Concert): Concert =
        Concert(
            name = concert.name,
            date = concert.date,
            id = sequence++
        ).also {
            store[it.id] = it
        }

    override fun findAllByDateGreaterThan(dateTime: LocalDateTime): List<Concert> =
        store.values.filter {
            dateTime.isBefore(it.date)
        }

}
