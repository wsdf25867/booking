package io.concert.booking.domain.queue

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class TokenRepositoryTest {

    private val sut: TokenRepository = FakeTokenRepository()

    @Test
    fun `토큰을 저장할 수 있다`() {
        // given
        val token = Token(userId = 1, concertId = 1)

        // when
        val saved = sut.save(token)

        // then
        assertThat(saved.id).isNotEqualTo(0)
    }

    @Test
    fun `토큰 uuid를 통해서 토큰을 조회할 수 있다`() {
        // given
        val uuid = UUID.randomUUID()
        val token = Token(token = uuid, userId = 1L, concertId = 1L)
        sut.save(token)
        // when
        val found = sut.findByToken(uuid)

        // then
        assertThat(found).isNotNull
        assertThat(found).extracting("userId", "concertId")
            .containsExactly(1L, 1L)
    }

    @Test
    fun `상태에 따라 토큰을 카운트 할 수 있다`() {
        // given
        val token = Token(userId = 1, concertId = 1)
        sut.save(token)

        // when
        val waitCount = sut.countByTokenStatus(TokenStatus.WAIT)
        val passCount = sut.countByTokenStatus(TokenStatus.PASS)

        // then
        assertThat(waitCount).isEqualTo(1)
        assertThat(passCount).isEqualTo(0)
    }

    @Test
    fun `입력된 id 보다 빨리 만들어진 토큰을 카운트 할 수 있다`() {
        // given
        val token = Token(userId = 1, concertId = 1)
        sut.save(token)

        // when
        val count = sut.countByStatusAndIdLessThan(TokenStatus.WAIT, 2)

        // then
        assertThat(count).isEqualTo(1)
    }
}

