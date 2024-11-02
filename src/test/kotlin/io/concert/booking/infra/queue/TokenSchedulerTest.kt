package io.concert.booking.infra.queue

import io.concert.booking.infra.config.SchedulerProperties
import io.concert.booking.domain.queue.FakeTokenRepository
import io.concert.booking.domain.queue.Token
import io.concert.booking.domain.queue.TokenStatus
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class TokenSchedulerTest {

    private val tokenRepository = FakeTokenRepository()
    private val sut = TokenScheduler(SchedulerProperties(50), tokenRepository)

    @Test
    fun `스케줄러는 지정된 개수의 토큰을 PASS 상태로 변경시킨다`() {
        // given
        repeat(50) {
            val token = Token(userId = 1L, concertId = 1L)
            tokenRepository.save(token)
        }
        val uuid = UUID.randomUUID()
        val token = Token(uuid = uuid, userId = 1L, concertId = 1L)
            tokenRepository.save(token)

        // when
        sut.passTokenEveryMinute()

        // then
        val found = tokenRepository.findByUuid(uuid)
        assertThat(found).extracting("status", "id")
            .containsExactly(TokenStatus.WAIT, 51L)
    }
}
