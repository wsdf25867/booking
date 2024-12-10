package io.concert.booking.application.concert.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ConcertRepository: JpaRepository<Concert, Long> {
    fun findAllByDateGreaterThan(dateTime: LocalDateTime): List<Concert>
}
