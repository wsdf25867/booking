package io.ryan.booking.domain.concert.domain

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface SeatRepository : JpaRepository<Seat, Long> {

    fun save(seat: Seat): Seat

    @Query("select s from Seat s where s.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByIdWithLock(id: Long): Seat?

    fun findAllByIdIn(seatIds: List<Long>): List<Seat>
    fun findAllByConcertIdAndStatus(concertId: Long, status: SeatStatus): List<Seat>
}
