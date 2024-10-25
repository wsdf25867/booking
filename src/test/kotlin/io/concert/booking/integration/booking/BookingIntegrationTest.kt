package io.concert.booking.integration.booking

import io.concert.booking.application.booking.BookingService
import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.application.booking.dto.BookingDto
import io.concert.booking.domain.booking.BookingRepository
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.queue.Token
import io.concert.booking.domain.queue.TokenRepository
import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class BookingIntegrationTest {

    @Autowired
    private lateinit var concertRepository: ConcertRepository

    @Autowired
    private lateinit var seatRepository: SeatRepository

    @Autowired
    private lateinit var tokenRepository: TokenRepository

    @Autowired
    private lateinit var bookingRepository: BookingRepository

    @Autowired
    private lateinit var sut: BookingService

    @BeforeEach
    fun setUp() {
        val concert = Concert("some concert", date = LocalDateTime.of(2999, 12, 31, 23, 59, 59))
        concertRepository.save(concert)

        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)
    }

    @AfterEach
    fun tearDown() {
        (bookingRepository as JpaRepository<*, *>).deleteAllInBatch()
        (seatRepository as JpaRepository<*, *>).deleteAllInBatch()
    }

    @Test
    fun `예약을 만들 수 있다`() {
        // given
        val token = Token(userId = 1, concertId = 1L)
        tokenRepository.save(token)
        // when
        val bookingDto: BookingDto = sut.create(BookingCreateDto(seatId = 1L, token.token.toString()))

        // then
        assertThat(bookingDto).extracting("bookingId", "seatId", "userId")
            .containsExactly(1L, 1L, 1L)
    }

    @Test
    fun `한자리에 하나만 예약이 가능하다`() {
        // given
        val token = Token(userId = 1, concertId = 1L)
        tokenRepository.save(token)
        val count = 10
        val letch = CountDownLatch(count)
        val threadPool = Executors.newFixedThreadPool(count)
        val results = mutableListOf<Boolean>()
        // when
        repeat(count) {
            threadPool.execute {
                try {
                    sut.create(BookingCreateDto(seatId = 1L, token.token.toString()))
                    results.add(true)
                } catch (e: Exception) {
                    results.add(false)
                } finally {
                    letch.countDown()
                }
            }
        }
        letch.await()

        // then
        val success = results.count { it }
        val fail = results.count { !it }

        assertThat(success).isEqualTo(1)
        assertThat(fail).isEqualTo(9)
    }
}
