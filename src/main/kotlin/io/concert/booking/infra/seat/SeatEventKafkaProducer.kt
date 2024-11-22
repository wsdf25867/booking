package io.concert.booking.infra.seat

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.domain.seat.SeatBookedEvent
import io.concert.booking.domain.seat.SeatEventProducer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class SeatEventKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
): SeatEventProducer {

    override fun send(event: SeatBookedEvent) {
        kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(event))
    }

    companion object {
        private const val TOPIC = "seat-booked"
    }
}
