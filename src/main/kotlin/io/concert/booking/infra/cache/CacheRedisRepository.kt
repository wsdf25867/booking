package io.concert.booking.infra.cache

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.domain.cache.CacheRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class CacheRedisRepository(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper,
) : CacheRepository<String, String> {

    override fun <T> findById(id: String, type: Class<T>): T? {
        return redisTemplate.opsForValue().get(id)
            .let { objectMapper.readValue(it, type) }
            ?: null
    }

    override fun <T> insert(id: String, value: T): T {
        return objectMapper.writeValueAsString(value)
            .let { redisTemplate.opsForValue().set(id, it) }
            .let { value }
    }


}