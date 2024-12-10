package io.concert.booking.application.seat.infra

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.application.outbox.domain.Outbox
import io.concert.booking.application.outbox.domain.OutboxRepository
import io.concert.booking.application.booking.domain.BookingCreatedEvent
import io.concert.booking.application.seat.domain.SeatBookedEventHandler
import io.concert.booking.application.seat.domain.SeatRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SeatBookedKafkaEventHandler(
    private val seatRepository: SeatRepository,
    private val outboxRepository: OutboxRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
) : SeatBookedEventHandler {

    @TransactionalEventListener(BookingCreatedEvent::class, phase = TransactionPhase.BEFORE_COMMIT)
    fun beforeCommit(event: BookingCreatedEvent) {
        outboxRepository.save(
            Outbox(
                DOMAIN_NAME,
                TOPIC,
                event.seatId,
                BookingCreatedEvent::class.java.name,
                objectMapper.writeValueAsString(event)
            )
        )
    }

    @TransactionalEventListener(BookingCreatedEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun afterCommitHandle(event: BookingCreatedEvent) {
        kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(event))
        val outbox = requireNotNull(outboxRepository.findByTopicAndDomainId(TOPIC, event.seatId))
        outbox.sent()
    }


    @Transactional
    @KafkaListener(topics = ["seat-booked"], groupId = "seats")
    fun handle(message: String) {
        val event = objectMapper.readValue(message, BookingCreatedEvent::class.java)
        val outbox = requireNotNull(outboxRepository.findByTopicAndDomainId(TOPIC, event.seatId))
        outbox.processed()
        handle(event)
    }

    override fun handle(event: BookingCreatedEvent) {
        val seat = requireNotNull(seatRepository.findByIdOrNull(event.seatId))
        seat.occupied()
    }

    companion object {
        private const val DOMAIN_NAME = "seat"
        private const val TOPIC = "seat-booked"
    }
}