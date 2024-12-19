package io.ryan.booking.domain.booking.application

import io.ryan.booking.domain.booking.domain.Booking
import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.concert.domain.ConcertScheduleRepository
import io.ryan.booking.domain.concert.domain.ConcertScheduleSeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Clock
import java.time.LocalDateTime

@Service
class BookingService(
    private val clock: Clock,
    private val bookingRepository: BookingRepository,
    private val concertScheduleRepository: ConcertScheduleRepository,
    private val concertScheduleSeatRepository: ConcertScheduleSeatRepository,
) {

    @Transactional
    fun create(request: BookingCreateServiceRequest): Long {
        val (userId, scheduleId, seatIds) = request

        val concertSchedule = concertScheduleRepository.findByIdOrNull(scheduleId)
            ?: throw EntityNotFoundException()
        val seats = concertScheduleSeatRepository.findAllById(seatIds)

        val bookedAt = LocalDateTime.now(clock)
        val booking = Booking(userId, bookedAt, concertSchedule, seats)
        return bookingRepository.save(booking).id
    }
}
