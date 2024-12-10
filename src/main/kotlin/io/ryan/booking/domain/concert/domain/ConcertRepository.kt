package io.ryan.booking.domain.concert.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ConcertRepository: JpaRepository<Concert, Long> {
    fun findAllByDateGreaterThan(dateTime: LocalDateTime): List<Concert>
}
