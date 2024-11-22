package io.concert.booking.domain.seat

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.domain.outbox.Outbox
import io.concert.booking.domain.outbox.OutboxRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SeatBookedEventHandler(
    private val outboxRepository: OutboxRepository,
    private val seatProducer: SeatEventProducer,
    private val objectMapper: ObjectMapper,
) {

    @Async
    @TransactionalEventListener(SeatBookedEvent::class, phase = TransactionPhase.BEFORE_COMMIT)
    fun beforeHandle(event: SeatBookedEvent) {
        val outbox = Outbox(
            DOMAIN_NAME,
            event.seatId,
            SeatBookedEvent::class.java.name,
            objectMapper.writeValueAsString(event)
        )
        outboxRepository.save(outbox)
    }

    @Async
    @TransactionalEventListener(SeatBookedEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun afterHandle(event: SeatBookedEvent) {
        seatProducer.send(event)
    }

    companion object {
        private const val DOMAIN_NAME = "seat"
    }
}
