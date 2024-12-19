package io.ryan.booking.domain.booking.infra

import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.booking.domain.BookingStatus
import io.ryan.booking.domain.concert.domain.SeatRepository
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
            bookingRepository.findAllByStatusAndConfirmableDateTimeBefore(BookingStatus.TEMPORARILY_HELD, targetTime)

        bookings.forEach { it.cancelled() }
    }
}
