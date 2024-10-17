package io.concert.booking.domain.point

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class PointRepositoryTest {

    private val sut: PointRepository = FakePointRepository()

    @Test
    fun `Point를 저장할 수 있다`() {
        // given
        val point = Point(1)

        // when
        val saved = sut.save(point)

        // then
        assertThat(saved.id).isEqualTo(1)
    }

    @Test
    fun `userId로 포인트를 조회할 수 있다`() {
        // given
        val point = Point(1)
        sut.save(point)

        // when
        val found = sut.findByUserId(1)

        // then
        assertThat(found).isNotNull
    }
}
