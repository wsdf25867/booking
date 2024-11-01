package io.concert.booking.application.queue

import io.concert.booking.application.queue.dto.TokenGenerateParam
import io.concert.booking.application.queue.dto.TokenSearchCond
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.concert.FakeConcertRepository
import io.concert.booking.domain.queue.FakeTokenRepository
import io.concert.booking.domain.queue.Token
import io.concert.booking.domain.queue.TokenRepository
import io.concert.booking.domain.user.FakeUserRepository
import io.concert.booking.domain.user.User
import io.concert.booking.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import java.util.*


class TokenApplicationServiceTest {

    private val tokenRepository: TokenRepository = FakeTokenRepository()
    private val userRepository: UserRepository = FakeUserRepository()
    private val concertRepository: ConcertRepository = FakeConcertRepository()
    private val sut = TokenApplicationService(tokenRepository, userRepository, concertRepository)

    @Test
    fun `토큰 생성시 유저정보가 없으면 IllegalArgumentException이 발생한다`() {
        // given
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
          sut.generateToken(TokenGenerateParam(userId = 1, concertId = 1))
        }.withMessageContaining("유저 정보")

    }

    @Test
    fun `토큰 생성시 콘서트 정보가 없으면 IllegalArgumentException 예외가 발생한다`() {
        // given
        userRepository.save(User("some name", 1))
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
          sut.generateToken(TokenGenerateParam(userId = 1, concertId = 1))
        }.withMessageContaining("콘서트 정보")
    }

    @Test
    fun `토큰 생성`() {
        // given
        userRepository.save(User("some name", 1))
        concertRepository.save(Concert("some concert"))
        val param = TokenGenerateParam(userId = 1, concertId = 1)

        // when
        val tokenResponse = sut.generateToken(param)

        // then
        assertThat(tokenResponse.token).isNotNull()
    }

    @Test
    fun `토큰 조회시 정보가 없으면 IllegalArgumentException 발생`() {
        // given
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.findToken(TokenSearchCond(UUID.randomUUID()))
        }.withMessageContaining("토큰 정보")
    }

    @Test
    fun `토큰 정보 조회`() {
        // given
        userRepository.save(User("some name", 1))
        concertRepository.save(Concert("some concert"))
        val saved = tokenRepository.save(Token(userId = 1, concertId = 1))
        val param = TokenSearchCond(saved.token)

        // when
        val found = sut.findToken(param)

        // then
        assertThat(found.queueIndex).isEqualTo(0)
        assertThat(found.queueSize).isEqualTo(1)
        assertThat(found.token).isEqualTo(saved.token)
    }
}
