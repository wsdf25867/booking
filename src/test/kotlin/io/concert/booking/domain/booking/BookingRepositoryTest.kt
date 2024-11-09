package io.concert.booking.domain.booking

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BookingRepositoryTest {

    private val sut: BookingRepository = FakeBookingRepository()

    @Test
    fun `예약을 저장할 수 있다`() {
        // given
        val booking = Booking(1L, 1L, 1000.toBigDecimal())

        // when
        val saved = sut.save(booking)

        // then
        assertThat(saved.id).isEqualTo(1)
    }

    @Test
    fun `id로 예약을 조회할 수 있다`() {
        // given
        val booking = Booking(1L, 1L, 1000.toBigDecimal())
        sut.save(booking)

        // when
        val actual = sut.findById(1L)

        // then
        assertThat(actual).isNotNull
    }
}
