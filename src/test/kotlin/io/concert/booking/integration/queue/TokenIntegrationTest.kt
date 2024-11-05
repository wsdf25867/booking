package io.concert.booking.integration.queue

import io.concert.booking.application.queue.TokenApplicationService
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.user.User
import io.concert.booking.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TokenIntegrationTest {

    @Autowired
    private lateinit var concertRepository: ConcertRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var sut: TokenApplicationService

    @Test
    fun `토큰을 발급 할 수 있다`() {
        // given
        val user = User("some user")
        val savedUser = userRepository.save(user)
        val concert = Concert("some concert", date = LocalDateTime.of(2999, 12, 31, 23, 59, 59))
        val savedConcert = concertRepository.save(concert)
        // when
        val generateToken = sut.createToken(savedUser.id, savedConcert.id)
        // then
        assertThat(generateToken.userId).isEqualTo(1)
        assertThat(generateToken.concertId).isEqualTo(1)
    }

    @Test
    fun `토큰을 조회 할 수 있다`() {
        // given
        val user = User("some user")
        val savedUser = userRepository.save(user)
        val concert = Concert("some concert", date = LocalDateTime.of(2999, 12, 31, 23, 59, 59))
        val savedConcert = concertRepository.save(concert)
        val generateToken = sut.createToken(savedUser.id, savedConcert.id)
        // when
        val findToken = sut.getTokenQueueInfo(generateToken.uuid)
        // then
        assertThat(findToken.queueSize).isEqualTo(1)
        assertThat(findToken.queueIndex).isEqualTo(0)
    }
}
