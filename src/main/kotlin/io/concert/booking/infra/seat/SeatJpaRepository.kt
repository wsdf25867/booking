package io.concert.booking.infra.seat

import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatRepository
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface SeatJpaRepository: JpaRepository<Seat, Long>, SeatRepository {

    @Query("select s from Seat s where s.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findByIdWithLock(id: Long): Seat?
}
