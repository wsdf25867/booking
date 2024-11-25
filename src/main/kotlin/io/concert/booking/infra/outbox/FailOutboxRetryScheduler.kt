package io.concert.booking.infra.outbox

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.domain.outbox.OutboxRepository
import io.concert.booking.domain.outbox.OutboxStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class FailOutboxRetryScheduler(
    private val outboxRepository: OutboxRepository,
    private val objectMapper: ObjectMapper,
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {

    @Scheduled(fixedRate = 1000)
    fun retry() {
        outboxRepository.findAllByStatus(OutboxStatus.PENDING).forEach {
            kafkaTemplate.send(it.topic, objectMapper.writeValueAsString(it.payload))
            it.sent()
        }
    }
}
