package io.concert.booking.domain.point

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class PointHistoryRepositoryTest {

    private val sut: PointHistoryRepository = FakePointHistoryRepository()

    @Test
    fun `포인트 이력을 저장할 수 있다`() {
        // given
        val pointHistory = PointHistory(1, 100.toBigDecimal(), PointOperationType.USE)

        // when
        val saved = sut.save(pointHistory)

        // then
        assertThat(saved.id).isEqualTo(1)
    }

    @Test
    fun `포인트 id로 포인트 이력을 조회할 수 있다`() {
        // given
        val pointHistory = PointHistory(1, 100.toBigDecimal(), PointOperationType.USE)
        val saved = sut.save(pointHistory)

        // when
        val actual = sut.findAllByPointId(1L)

        // then
        assertThat(actual).hasSize(1)
    }
}
