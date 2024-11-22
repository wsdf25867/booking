package io.concert.booking.infra.seat

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.domain.seat.SeatBookedEvent
import io.concert.booking.domain.seat.SeatEventConsumer
import io.concert.booking.domain.seat.SeatRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SeatEventKafkaConsumer(
    private val seatRepository: SeatRepository,
    private val objectMapper: ObjectMapper,
) : SeatEventConsumer {

    @Transactional
    @KafkaListener(topics = ["seat-booked"], groupId = "seats")
    override fun consume(message: String) {
        val event = objectMapper.readValue(message, SeatBookedEvent::class.java)
        val seat = requireNotNull(seatRepository.findById(event.seatId))
        seat.occupied()
    }
}
