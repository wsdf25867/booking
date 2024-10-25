package io.concert.booking.integration.point

import io.concert.booking.application.point.PointService
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
    private lateinit var sut: PointService

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

        val booking = Booking(1L, 1L)
        bookingRepository.save(booking)
    }

    @AfterEach
    fun tearDown() {
        (pointRepository as JpaRepository<*, *>).deleteAllInBatch()
    }

    @Test
    fun `포인트를 충전할 수 있다`() {
        // given

        // when
        val pointDto = sut.charge(PointChargeDto(1L, 100.toBigDecimal()))
        // then

        Assertions.assertThat(pointDto.balance.compareTo(200.toBigDecimal())).isEqualTo(0)
    }

    @Test
    fun `포인트를 동시에 충전할 수 있다`() {
        // given
        val newPoint = Point(userId = 2L)
        pointRepository.save(newPoint)
        val count = 50
        val letch = CountDownLatch(count)
        val pool = Executors.newFixedThreadPool(count)

        // when
        for (i in 1..count) {

            pool.execute {
                try {
                    sut.charge(PointChargeDto(2L, 100.toBigDecimal()))
                } finally {
                    letch.countDown()
                }
            }
        }

        letch.await()
        // then
        val point = pointRepository.findByUserId(2L) ?: throw IllegalArgumentException()
        Assertions.assertThat(point.balance.compareTo(5000.toBigDecimal())).isEqualTo(0)
    }
}
