package io.concert.booking.application.outbox.infra

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.application.outbox.domain.OutboxRepository
import io.concert.booking.application.outbox.domain.OutboxStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class FailOutboxRetryScheduler(
    private val outboxRepository: OutboxRepository,
    private val objectMapper: ObjectMapper,
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {

    @Scheduled(fixedRate = 100000)
    fun retry() {
        outboxRepository.findAllByStatus(OutboxStatus.PENDING).forEach {
            kafkaTemplate.send(it.topic, objectMapper.writeValueAsString(it.payload))
            it.sent()
        }
    }
}
