package io.concert.booking.domain.cache

import java.util.concurrent.TimeUnit

interface CacheRepository {

    fun findById(id: String): String?

    fun insert(id: String, value: String): String

    fun insert(id: String, value: String, ttl: Long, unit: TimeUnit): String

    fun increase(id: String): Int
}
