package io.concert.booking.domain.seat

import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SeatBookedEventHandler(

) {

    @Async
    @TransactionalEventListener(SeatBookedEvent::class, phase = TransactionPhase.BEFORE_COMMIT)
    fun beforeHandle(event: SeatBookedEvent) {
        Outbox("seat", TOPIC, event.bookingId, SeatBookedEvent::class.java.name, event.toString())

    }

    @Async
    @TransactionalEventListener(SeatBookedEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun afterHandle(event: SeatBookedEvent) {

    }

    companion object {
        private const val TOPIC = "seat-booked"
    }
}
