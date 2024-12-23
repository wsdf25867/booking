package io.ryan.booking.domain.booking.infra

import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.booking.domain.BookingStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class NotPaidBookingCancelScheduler(
    private val bookingRepository: BookingRepository,
) {

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    fun cancel() {
        bookingRepository.findAllByStatusAndPayableDateTimeBefore(
            BookingStatus.TEMPORARILY_HELD,
            LocalDateTime.now()
        ).forEach { it.cancelled() }
    }
}
