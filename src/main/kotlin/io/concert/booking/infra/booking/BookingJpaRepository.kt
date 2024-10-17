package io.concert.booking.infra.booking

import io.concert.booking.domain.booking.Booking
import io.concert.booking.domain.booking.BookingRepository
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface BookingJpaRepository : JpaRepository<Booking, Long>, BookingRepository {

    @Query("select b from Booking b where b.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findByIdWithLock(id: Long): Booking?

}
