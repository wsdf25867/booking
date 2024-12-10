package io.concert.booking.application.booking.domain

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface BookingRepository: JpaRepository<Booking, Long> {

    fun save(booking: Booking): Booking
    @Query("select b from Booking b where b.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByIdWithLock(id: Long): Booking?
    fun findAllByStatusAndCreatedAtBefore(status: BookingStatus, createdAt: LocalDateTime): List<Booking>

}
