package io.concert.booking.domain.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatRepositoryTest {

    private val sut: SeatRepository = FakeSeatRepository()

    @Test
    fun `좌석을 저장할 수 있다`() {
        // given
        val seat = Seat(concertId = 1, seatNumber = 1, 1000.toBigDecimal())

        // when
        val saved = sut.save(seat)

        // then
        assertThat(saved.id).isNotNull
    }

    @Test
    fun `id로 좌석을 조회할 수 있다`() {
        // given
        val seat = Seat(concertId = 1, seatNumber = 1, 1000.toBigDecimal())
        val saved = sut.save(seat)

        // when
        val found = sut.findById(saved.id)

        // then
        assertThat(found).extracting("id", "concertId", "seatNumber", "price")
            .containsExactly(1L, 1L, 1, 1000.toBigDecimal())
    }
}
