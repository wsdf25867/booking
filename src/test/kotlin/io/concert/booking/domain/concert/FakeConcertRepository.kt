package io.concert.booking.domain.concert

class FakeConcertRepository : ConcertRepository {
    private val store: MutableMap<Long, Concert> = mutableMapOf()
    private var sequence: Long = 1

    override fun findById(id: Long): Concert? {
        return store[id]
    }

    override fun save(concert: Concert): Concert =
        Concert(
            name = concert.name,
            id = sequence++
        ).also {
            store[it.id] = it
        }


}
