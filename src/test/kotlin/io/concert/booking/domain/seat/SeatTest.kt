package io.concert.booking.domain.seat

import io.concert.booking.domain.user.User
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test

class SeatTest {

    @Test
    fun `좌석을 예약시 이미 선택된 좌석이라면 IllegalStateException 발생`() {
        // given
        val seat = Seat(1L, 1, 100.toBigDecimal(), SeatStatus.OCCUPIED)

        // when
        // then
        assertThatIllegalStateException().isThrownBy {
            seat.bookBy(User("강지우"))
        }.withMessageContaining("이미")
    }
}
