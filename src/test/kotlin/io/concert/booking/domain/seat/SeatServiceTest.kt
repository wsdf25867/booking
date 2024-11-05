package io.concert.booking.domain.seat

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SeatServiceTest {

    private val service = SeatService(FakeSeatRepository())

    @Test
    fun `좌석 조회시 좌석 정보가 존재하지 않으면 IllegalArgumentException 을 던진다`() {
        // given
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            service.get(1L)
        }
    }
}
