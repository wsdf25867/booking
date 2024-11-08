package io.concert.booking.domain.cache

interface CacheRepository<ID, V> {

    fun <T> findById(id: ID, type: Class<T>): T?

    fun <T> insert(id: ID, value: T): T
}