package io.ryan.booking.domain.cache.infra

import io.ryan.booking.domain.cache.domain.CacheRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

@Repository
class CacheRedisRepository(
    private val redisTemplate: RedisTemplate<String, String>,
) : CacheRepository {

    override fun findById(id: String): String? =
        redisTemplate.opsForValue().get(id)

    override fun insert(id: String, value: String): String =
        redisTemplate.opsForValue().set(id, value)
            .let { value }

    override fun insert(id: String, value: String, ttl: Long, unit: TimeUnit): String =
        redisTemplate.opsForValue().set(id, value, ttl, unit)
            .let { value }

    override fun increase(id: String): Int {
        return redisTemplate.opsForValue().increment(id)?.toInt() ?: 1
    }

}
