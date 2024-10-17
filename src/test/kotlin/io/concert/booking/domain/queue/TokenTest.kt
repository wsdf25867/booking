package io.concert.booking.domain.queue

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TokenTest {

    @Test
    fun `토큰 발급한지 5분이 지나고 pass 시 IllegalStateException 발생한다`() {
        // given
        val createdAt = LocalDateTime.of(1995, 3, 26, 0, 0)
        val token = Token(userId = 1, concertId = 1)

        // when // then
        assertThatIllegalStateException().isThrownBy {
            token.passedAt(createdAt.plusMinutes(5))
        }
    }

    @Test
    fun `토큰은 pass 될 수 있다`() {
        // given
        val createdAt = LocalDateTime.now()
        val token = Token(userId = 1, concertId = 1)

        // when
        token.passedAt(createdAt)

        // then
        assertThat(token.status).isEqualTo(TokenStatus.PASS)
    }
}
