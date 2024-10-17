package io.concert.booking.infra.booking

import io.concert.booking.domain.booking.Booking
import io.concert.booking.domain.booking.BookingRepository
import org.springframework.data.jpa.repository.JpaRepository

interface BookingJpaRepository: JpaRepository<Booking, Long>, BookingRepository {
}
