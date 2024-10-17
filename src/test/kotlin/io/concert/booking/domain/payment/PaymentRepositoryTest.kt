package io.concert.booking.domain.payment

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class PaymentRepositoryTest {

    private val sut: PaymentRepository = FakePaymentRepository()

    @Test
    fun `결제정보를 저장할 수 있다`() {
        // given
        val payment = Payment(1L, 100.toBigDecimal())

        // when
        val saved = sut.save(payment)

        // then
        assertThat(saved).extracting("bookingId", "amount")
            .containsExactly(1L, 100.toBigDecimal())
    }

}
