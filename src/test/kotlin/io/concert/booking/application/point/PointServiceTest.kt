package io.concert.booking.application.point

import io.concert.booking.application.point.dto.PointChargeDto
import io.concert.booking.domain.point.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class PointServiceTest {

    private val pointHistoryRepository: PointHistoryRepository = FakePointHistoryRepository()
    private val pointRepository: PointRepository = FakePointRepository()
    private val sut = PointService(pointRepository, pointHistoryRepository)

    @Test
    fun `포인트 조회시 정보가 없으면 IllegalArgumentException 발생`() {
        // given
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.find(1L)

        }.withMessageContaining("포인트 정보")
    }

    @Test
    fun `포인트 조회시 정보가 있으면 포인트 정보를 반환한다`() {
        // given
        val point = Point(1L)
        pointRepository.save(point)

        // when
        val found = sut.find(1L)

        // then
        assertThat(found.userId).isEqualTo(1L)
        assertThat(found.balance).isEqualTo(0.toBigDecimal())
    }

    @Test
    fun `포인트 충전시 정보가 없으면 IllegalArgumentException 발생`() {
        // given

        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.charge(PointChargeDto(1L, 100.toBigDecimal()))
        }.withMessageContaining("포인트 정보")
    }

    @Test
    fun `포인트 충전시 정보가 있으면 포인트가 잔액이 증가한다`() {
        // given
        val point = Point(1L)
        pointRepository.save(point)

        // when
        sut.charge(PointChargeDto(1L, 100.toBigDecimal()))

        // then
        val found = pointRepository.findByUserId(1L)
        assertThat(found).extracting("userId", "balance")
            .containsExactly(1L, 100.toBigDecimal())
    }

    @Test
    fun `포인트 충전에 성공하면 포인트 이력이 저장된다`() {
        // given
        val point = Point(1L)
        pointRepository.save(point)

        // when
        sut.charge(PointChargeDto(1L, 100.toBigDecimal()))

        // then
        val actualPoint = pointRepository.findByUserId(1L)
        assertThat(actualPoint).extracting("userId", "balance")
            .containsExactly(1L, 100.toBigDecimal())

        val histories = pointHistoryRepository.findAllByPointId(1L)
        assertThat(histories).isNotEmpty
    }
}
