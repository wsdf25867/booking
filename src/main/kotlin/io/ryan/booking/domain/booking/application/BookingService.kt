package io.ryan.booking.domain.booking.application

import io.ryan.booking.domain.booking.domain.Booking
import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.booking.domain.BookingSchedule
import io.ryan.booking.domain.booking.domain.BookingSeat
import io.ryan.booking.domain.concert.domain.ConcertScheduleRepository
import io.ryan.booking.domain.concert.domain.ConcertScheduleSeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookingService(
    private val concertScheduleRepository: ConcertScheduleRepository,
    private val concertScheduleSeatRepository: ConcertScheduleSeatRepository,
    private val bookingRepository: BookingRepository,
) {

    @Transactional
    fun create(request: BookingCreateCommand): Long {
        val (userId, scheduleId, seatIds) = request

        val concertSchedule = concertScheduleRepository.findByIdOrNull(scheduleId)
            ?: throw EntityNotFoundException("콘서트 정보가 없습니다. concertScheduleId: $scheduleId")

        val bookingSchedule = BookingSchedule(concertSchedule)

        val seats = concertScheduleSeatRepository.findAllById(seatIds)
            .map { BookingSeat(it) }.toSet()

        val booking = Booking(userId, bookingSchedule = bookingSchedule, bookingSeats = seats)

        return bookingRepository.save(booking).id
    }

}
