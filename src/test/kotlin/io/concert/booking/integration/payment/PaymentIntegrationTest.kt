package io.concert.booking.integration.payment

import io.concert.booking.application.payment.PaymentFacade
import io.concert.booking.application.payment.dto.PaymentCreateDto
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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@Transactional
class PaymentIntegrationTest {

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
    private lateinit var sut: PaymentFacade

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
    fun `결제 할 수 있다`() {
        // given
        // when
        val paymentDto = sut.create(PaymentCreateDto(userId = 1L, 1L, 1L, 1L))

        // then
        assertThat(paymentDto.paymentId).isEqualTo(1)
        assertThat(paymentDto.amount).isEqualTo(100.toBigDecimal())
    }
}
