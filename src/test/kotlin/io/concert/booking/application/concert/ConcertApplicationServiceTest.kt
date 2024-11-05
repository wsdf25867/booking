package io.concert.booking.application.concert

import io.concert.booking.application.concert.dto.ConcertSearchDto
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.concert.FakeConcertRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ConcertFacadeTest {

    private val concertRepository: ConcertRepository = FakeConcertRepository()
    private val sut = ConcertFacade(concertRepository)

    @Test
    fun `조회 요청이후 날짜에 공연하는 콘서트를 반환한다`() {
        // given
        val concert = Concert("some concert", LocalDateTime.of(1995, 3, 26, 0, 1))
        concertRepository.save(concert)

        val cond = ConcertSearchDto(LocalDateTime.of(1995, 3, 26, 0, 0))

        // when
        val bookable = sut.findAllBookable(cond)

        // then
        assertThat(bookable).hasSize(1)
    }

    @Test
    fun `조회 요청이후 날짜에 공연하는 콘서트가 없다면 빈 리스트를 반환한다`() {
        // given
        val concert = Concert("some concert", LocalDateTime.of(1995, 3, 26, 0, 0))
        concertRepository.save(concert)

        val cond = ConcertSearchDto(LocalDateTime.of(1995, 3, 26, 0, 0))

        // when
        val bookable = sut.findAllBookable(cond)

        // then
        assertThat(bookable).isEmpty()
    }

}
