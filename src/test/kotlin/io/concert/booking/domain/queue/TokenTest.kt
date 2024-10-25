package io.concert.booking.domain.queue

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TokenTest {

    @Test
    fun `토큰 발급한지 5분이 지나고 pass 시 IllegalStateException 발생한다`() {
        // given
        val issuedAt = LocalDateTime.of(1995, 3, 26, 0, 0)
        val token = Token(userId = 1, concertId = 1, issuedAt = issuedAt)

        // when // then
        assertThatIllegalStateException().isThrownBy {
            token.passedAt(issuedAt.plusMinutes(5).plusNanos(1))
        }
    }

    @Test
    fun `토큰은 pass 될 수 있다`() {
        // given
        val issuedAt = LocalDateTime.of(1995, 3, 26, 0, 0)
        val token = Token(userId = 1, concertId = 1, issuedAt = issuedAt)

        // when
        token.passedAt(issuedAt)

        // then
        assertThat(token.status).isEqualTo(TokenStatus.PASS)
    }
}
