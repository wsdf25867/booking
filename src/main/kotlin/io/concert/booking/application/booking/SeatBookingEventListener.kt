package io.concert.booking.application.booking

import io.concert.booking.domain.booking.SeatBookingEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SeatBookingEventListener(
    private val bookingDataPlatformClient: BookingDataPlatformClient
) {

    @Async
    @TransactionalEventListener(SeatBookingEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun onSeatBookingEvent(event: SeatBookingEvent) {
        bookingDataPlatformClient.send(event.bookingId)
    }
}
