package io.concert.booking.domain.booking

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class BookingRepositoryTest {

    private val sut: BookingRepository = FakeBookingRepository()

    @Test
    fun `예약을 저장할 수 있다`() {
        // given
        val booking = Booking(1L, 1L)

        // when
        val saved = sut.save(booking)

        // then
        assertThat(saved.id).isEqualTo(1)
    }
}
