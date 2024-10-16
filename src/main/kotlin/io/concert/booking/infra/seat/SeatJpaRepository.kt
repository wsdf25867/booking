package io.concert.booking.infra.seat

import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatRepository
import org.springframework.data.jpa.repository.JpaRepository

interface SeatJpaRepository: JpaRepository<Seat, Long>, SeatRepository {
}
