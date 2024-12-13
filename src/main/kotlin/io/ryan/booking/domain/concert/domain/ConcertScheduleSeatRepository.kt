package io.ryan.booking.domain.concert.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ConcertScheduleSeatRepository: JpaRepository<ConcertScheduleSeat, Long> {
}
