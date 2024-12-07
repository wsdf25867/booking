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

     @Test
     fun `토큰은 요청순서를 받아 패스가능 시간을 계산하고  갖고 있는다`() {
         /**
          * 요청 들어오면 redis count + 1 이결 현재 position 그리고
          * 번호에 따라 51번째면 issuedAt + (단위시간 * 1(n))
          * 101번째면 issuedAt + (단위시간 * 2)
          * 1 ~ 50 = 0 (a-1/50)
          * 51 ~ 100  = 1
          * 101 ~ 150 = 2
          *
          */
         // given
         val issuedAt = LocalDateTime.of(1995, 3, 26, 0, 0)
         val token = Token(userId = 1, concertId = 1, issuedAt = issuedAt, requestSequence = 1)

         // when
         // then
         assertThat(token.passableAt).isEqualTo(issuedAt)
     }


    @Test
    fun `50명마다 패스가능 시간이 10초 증가한다`() {
        // given
        val issuedAt = LocalDateTime.of(1995, 3, 26, 0, 0)
        val token = Token(userId = 1, concertId = 1, issuedAt = issuedAt, requestSequence = 51)

        // when
        // then
        assertThat(token.passableAt).isEqualTo(issuedAt.plusSeconds(Token.INTERVAL_SECONDS))
    }
}
