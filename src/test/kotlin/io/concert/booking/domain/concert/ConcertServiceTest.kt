package io.concert.booking.domain.concert

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ConcertServiceTest {

    private val sut = ConcertService(FakeConcertRepository())

    @Test
    fun `콘서트 정보가 없다면 IllegalArgumentException 을 던진다`() {
        // given
        // when
        // then
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            sut.get(1L)
        }
    }
}
