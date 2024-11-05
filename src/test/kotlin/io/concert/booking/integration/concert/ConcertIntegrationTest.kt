package io.concert.booking.integration.concert

import io.concert.booking.application.concert.ConcertFacade
import io.concert.booking.application.concert.dto.ConcertSearchDto
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@Transactional
class ConcertIntegrationTest {
    @Autowired
    private lateinit var concertRepository: ConcertRepository
    @Autowired
    private lateinit var sut: ConcertFacade

    @BeforeEach
    fun setUp() {
        val concert = Concert("some concert", date = LocalDateTime.of(2999, 12, 31, 23, 59, 59))
        concertRepository.save(concert)
    }

    @Test
    fun `콘서트를 조회할 수 있다`() {
        // given
        // when
        val concerts = sut.findAllBookable(ConcertSearchDto(LocalDateTime.of(1995, 3, 26, 0, 0)))

        // then
        assertThat(concerts).hasSize(1)
    }
}
