package io.concert.booking.integration.point

import io.concert.booking.application.point.PointFacade
import io.concert.booking.application.point.dto.PointChargeDto
import io.concert.booking.domain.booking.Booking
import io.concert.booking.domain.booking.BookingRepository
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.point.Point
import io.concert.booking.domain.point.PointRepository
import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatRepository
import io.concert.booking.domain.user.User
import io.concert.booking.domain.user.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@Transactional
class PointIntegrationTest {

    @Autowired
    private lateinit var pointRepository: PointRepository

    @Autowired
    private lateinit var concertRepository: ConcertRepository

    @Autowired
    private lateinit var seatRepository: SeatRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bookingRepository: BookingRepository

    @Autowired
    private lateinit var sut: PointFacade

    @BeforeEach
    fun setUp() {
        val user = User("some user")
        userRepository.save(user)

        val point = Point(1L, 100.toBigDecimal())
        pointRepository.save(point)

        val concert = Concert("some concert", date = LocalDateTime.of(2999, 12, 31, 23, 59, 59))
        concertRepository.save(concert)

        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)

        val booking = Booking(1L, 1L, seat.price)
        bookingRepository.save(booking)
    }

    @Test
    fun `포인트를 충전할 수 있다`() {
        // given

        // when
        val pointDto = sut.charge(PointChargeDto(1L, 100.toBigDecimal()))
        // then

        Assertions.assertThat(pointDto.balance).isEqualTo(200.toBigDecimal())
    }
}
