package io.concert.booking.infra.booking

import io.concert.booking.domain.booking.BookingRepository
import io.concert.booking.domain.booking.BookingStatus
import io.concert.booking.domain.seat.SeatRepository
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
            bookingRepository.findAllByStatusAndCreatedAtBefore(BookingStatus.TEMPORARY, targetTime)
        val seatIds = bookings.map { it.seatId }
        val seats = seatRepository.findAllByIds(seatIds)

        try {
            seats.forEach{ it.empty() }
            bookings.forEach { it.cancelled() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
