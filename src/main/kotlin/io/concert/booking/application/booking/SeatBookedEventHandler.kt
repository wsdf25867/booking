package io.concert.booking.application.booking

import io.concert.booking.domain.seat.SeatBookedEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SeatBookedEventHandler(
    private val bookingDataPlatformClient: BookingDataPlatformClient
) {

    @Async
    @TransactionalEventListener(SeatBookedEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun onSeatBookingEvent(event: SeatBookedEvent) {
        bookingDataPlatformClient.send(event.bookingId)
    }
}
