package io.ryan.booking.domain.booking.domain

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface BookingRepository: JpaRepository<Booking, Long> {

    @Query("select b from Booking b where b.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByIdWithLock(id: Long): Booking?

    @Query("select b from Booking b " +
            "where b.status = :status " +
            "and b.bookingConcertSchedule.confirmableDateTime <= :currentDateTime")
    fun findAllByStatusAndConfirmableDateTimeBefore(status: BookingStatus, currentDateTime: LocalDateTime): List<Booking>

}
