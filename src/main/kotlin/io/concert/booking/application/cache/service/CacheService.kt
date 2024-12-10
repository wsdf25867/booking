package io.concert.booking.application.cache.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.application.cache.domain.CacheRepository
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class CacheService(
    private val cacheRepository: CacheRepository,
    private val objectMapper: ObjectMapper,
) {

    fun <ID, T> get(id: ID, entityType: Class<T>): T? {
        val key = entityType.name + objectMapper.writeValueAsString(id)
        return cacheRepository.findById(key)
            ?.let { objectMapper.readValue(it, entityType) }
    }

    fun <ID, T> get(id: ID, entityType: TypeReference<T>): T? {
        val key = entityType.type.typeName + objectMapper.writeValueAsString(id)
        return cacheRepository.findById(key)
            ?.let { objectMapper.readValue(it, entityType) }
    }

    fun <ID, T> set(id: ID, value: T, ttl: Long, unit: TimeUnit, entityType: Class<T>): T {
        val key = entityType.name + objectMapper.writeValueAsString(id)
        val stringValue = objectMapper.writeValueAsString(value)
        return cacheRepository.insert(key, stringValue, ttl, unit)
            .let { value }
    }

    fun <ID, T> set(id: ID, value: T, ttl: Long, unit: TimeUnit, entityType: TypeReference<T>): T {
        val key = entityType.type.typeName + objectMapper.writeValueAsString(id)
        val stringValue = objectMapper.writeValueAsString(value)
        return cacheRepository.insert(key, stringValue, ttl, unit)
            .let { value }
    }

    fun <ID> increase(id: ID): Int {
        return cacheRepository.increase(objectMapper.writeValueAsString(id))
    }
}
