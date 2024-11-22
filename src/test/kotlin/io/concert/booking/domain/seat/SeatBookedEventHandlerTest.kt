package io.concert.booking.domain.seat

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class SeatBookedEventHandlerTest {

    private val seatRepository = FakeSeatRepository()
    private val eventHandler = SeatBookedEventHandler(seatRepository)

    @Test
    fun `이벤트를 수신해서 좌석정보가 없으면 예외를 IllegalArgumentException 을 발생시킨다`() {
        // given
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            eventHandler.handle(SeatBookedEvent(seatId = 1))
        }
    }

    @Test
    fun `이벤트를 수신해서 좌석정보가 있다면 occupied로 상태를 변경한다`() {
        // given
        seatRepository.save(Seat(1L, 1, 100.toBigDecimal()))

        // when
        eventHandler.handle(SeatBookedEvent(seatId = 1))

        // then
        val seat = seatRepository.findById(1L)
        assertThat(seat?.status).isEqualTo(SeatStatus.OCCUPIED)
    }
}
