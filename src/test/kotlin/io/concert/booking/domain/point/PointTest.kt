package io.concert.booking.domain.point

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PointTest {

    @Test
    fun `포인트 충전시 양수가 아니면 IllegalArgumentException 이 발생한다`() {
        // given
        val point = Point(1L)

        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            point.charge(0.toBigDecimal())
        }
    }

    @Test
    fun `포인트 충전에 성공하면 잔액이 증가한다`() {
        // given
        val point = Point(1L)

        // when
        point.charge(100.toBigDecimal())

        // then
        assertThat(point.balance).isEqualTo(100.toBigDecimal())
    }

    @Test
    fun `포인트 차감시 양수가 아닐시 IllegalArgumentException 발생`() {
        // given
        val point = Point(1L, balance = 100.toBigDecimal())
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            point.use(BigDecimal.ZERO)
        }
    }

    @Test
    fun `포인트 차감금액이 잔액보다 크다면 IllegalStateException 발생`() {
        // given
        val point = Point(1L, balance = 100.toBigDecimal())

        // when
        // then
        assertThatIllegalStateException().isThrownBy {
            point.use(101.toBigDecimal())
        }
    }

    @Test
    fun `포인트 차감 성공시 잔액 감소`() {
        // given
        val point = Point(1L, balance = 100.toBigDecimal())

        // when
        point.use(100.toBigDecimal())

        // then
        assertThat(point.balance).isEqualTo(BigDecimal.ZERO)
    }
}
