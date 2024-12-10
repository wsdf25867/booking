package io.concert.booking.application.booking.infra

import io.concert.booking.application.booking.domain.BookingRepository
import io.concert.booking.application.booking.domain.BookingStatus
import io.concert.booking.application.seat.domain.SeatRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
@Transactional
class BookingScheduler(
    private val bookingRepository: BookingRepository,
    private val seatRepository: SeatRepository,
) {

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    fun expiredBooking() {
        val targetTime = LocalDateTime.now().minusMinutes(5)
        val bookings =
            bookingRepository.findAllByStatusAndCreatedAtBefore(BookingStatus.TEMPORARILY_HELD, targetTime)
        val seatIds = bookings.map { it.seatId }
        val seats = seatRepository.findAllByIdIn(seatIds)

        try {
            seats.forEach{ it.empty() }
            bookings.forEach { it.cancelled() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
