package io.concert.booking.integration.booking

import io.concert.booking.application.booking.BookingService
import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.application.booking.dto.BookingDto
import io.concert.booking.domain.booking.BookingRepository
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatRepository
import io.concert.booking.domain.user.User
import io.concert.booking.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@Transactional
class BookingIntegrationTest {

    @Autowired
    private lateinit var concertRepository: ConcertRepository

    @Autowired
    private lateinit var seatRepository: SeatRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bookingRepository: BookingRepository

    @Autowired
    private lateinit var bookingService: BookingService

    @BeforeEach
    fun setUp() {
        val user = User("some user")
        userRepository.save(user)

        val concert = Concert("some concert", date = LocalDateTime.of(2999, 12, 31, 23, 59, 59))
        concertRepository.save(concert)

        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)
    }

    @Test
    fun `예약을 만들 수 있다`() {
        // given
        // when
        val bookingDto: BookingDto = bookingService.create(BookingCreateDto(seatId = 1L, userId = 1L))

        // then
        assertThat(bookingDto).extracting("bookingId", "seatId", "userId")
            .containsExactly(1L, 1L, 1L)
    }
}
