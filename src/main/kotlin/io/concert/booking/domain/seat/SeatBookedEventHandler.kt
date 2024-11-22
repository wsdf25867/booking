package io.concert.booking.domain.seat

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SeatBookedEventHandler(
    private val seatRepository : SeatRepository,
) {

    @Transactional
    @TransactionalEventListener(SeatBookedEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: SeatBookedEvent) {
        val seat = requireNotNull(seatRepository.findById(event.seatId)) {
            "좌석 정보가 없습니다."
        }
        seat.occupied()
    }
}
