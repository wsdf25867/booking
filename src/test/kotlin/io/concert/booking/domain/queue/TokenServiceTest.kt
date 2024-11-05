package io.concert.booking.domain.queue

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test
import java.util.*

class TokenServiceTest {

    private val sut: TokenService = TokenService(FakeTokenRepository())

    @Test
    fun `userId와 concertId로 토큰을 생성할 수 있다`() {
        // given
        // when
        val actual = sut.create(1L, 1L)

        // then
        assertThat(actual.userId).isEqualTo(1L)
        assertThat(actual.concertId).isEqualTo(1L)
    }

    @Test
    fun `uuid 로 토큰 조회시 정보가 없으면 IllegalArgumentException 을 반한다`() {
        // given
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.getByUuid(UUID.randomUUID())
        }
    }

    @Test
    fun `현재 대기열의 대기중인 토큰의 수를 반환한다`() {
        // given
        sut.create(1L, 1L)

        // when
        val actual = sut.getWaitingQueueSize()

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `현재 토큰의 순서를 반환한다`() {
        // given
        val token = sut.create(1L, 1L)

        // when
        val actual = sut.getWaitingQueueIndex(token.id)

        // then
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `토큰이 패스상태가 아니라면 IllegalStateException`() {
        // given
        val token = sut.create(1L, 1L)
        // when
        // then
        assertThatIllegalStateException().isThrownBy {
            sut.validate(token)
        }
    }
}
