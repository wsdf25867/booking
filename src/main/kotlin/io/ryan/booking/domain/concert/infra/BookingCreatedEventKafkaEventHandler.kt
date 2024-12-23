package io.ryan.booking.domain.concert.infra

import com.fasterxml.jackson.databind.ObjectMapper
import io.ryan.booking.domain.outbox.domain.OutboxRepository
import io.ryan.booking.domain.concert.domain.BookingCreatedEventHandler
import io.ryan.booking.domain.concert.domain.SeatRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class BookingCreatedEventKafkaEventHandler(
    private val seatRepository: SeatRepository,
    private val outboxRepository: OutboxRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
) : BookingCreatedEventHandler {

//    @TransactionalEventListener(BookingCreatedEvent::class, phase = TransactionPhase.BEFORE_COMMIT)
//    fun beforeCommit(event: BookingCreatedEvent) {
//        outboxRepository.save(
//            Outbox(
//                DOMAIN_NAME,
//                TOPIC,
//                event.seatId,
//                BookingCreatedEvent::class.java.name,
//                objectMapper.writeValueAsString(event)
//            )
//        )
//    }
//
//    @TransactionalEventListener(BookingCreatedEvent::class, phase = TransactionPhase.AFTER_COMMIT)
//    fun afterCommitHandle(event: BookingCreatedEvent) {
//        kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(event))
//        val outbox = requireNotNull(outboxRepository.findByTopicAndDomainId(TOPIC, event.seatId))
//        outbox.sent()
//    }
//
//
//    @Async
//    @Transactional
//    @KafkaListener(topics = [TOPIC], groupId = "seats")
//    fun handle(message: String, ack: Acknowledgment) {
//        val event = objectMapper.readValue(message, BookingCreatedEvent::class.java)
//        val outbox = outboxRepository.findByTopicAndDomainId(TOPIC, event.seatId) ?: throw EntityNotFoundException()
//        outbox.processed()
//        ack.acknowledge()
//        handle(event)
//    }
//
//    override fun handle(event: BookingCreatedEvent) {
//        val seat = seatRepository.findByIdOrNull(event.seatId) ?: throw EntityNotFoundException()
//        seat.occupied()
//    }
//
//    companion object {
//        private const val DOMAIN_NAME = "seat"
//        private const val TOPIC = "booking-created"
//    }
}
