package io.concert.booking.infra.concert

import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import org.springframework.data.jpa.repository.JpaRepository

interface ConcertJpaRepository: JpaRepository<Concert, Long>, ConcertRepository {
}
