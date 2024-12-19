package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.Concert
import io.ryan.booking.domain.concert.domain.ConcertSchedule
import io.ryan.booking.domain.concert.domain.ConcertScheduleSeat
import io.ryan.booking.domain.concert.domain.Seat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@ActiveProfiles("test")
@DataJpaTest
class BookingRepositoryTest {

    @Autowired
    private lateinit var sut: BookingRepository

    @Test
    fun `임시 예약 상태이면서 예약한지 5분이 지난 예약을 가져온다`() {
        // given
        val bookedAt = LocalDateTime.now()
        val concertSchedule = ConcertSchedule(Concert("some concert", "some artist", "some place"))
        val seats = listOf(ConcertScheduleSeat(concertSchedule, Seat(1, 100.toBigDecimal())))
        val booking = Booking(0, bookedAt, concertSchedule, seats)
        sut.save(booking)

        // when
        val actual = sut.findAllByStatusAndConfirmableDateTimeBefore(
            BookingStatus.TEMPORARILY_HELD,
            bookedAt.plus(Duration.of(6, ChronoUnit.MINUTES))
        )

        // then
        assertThat(actual).hasSize(1)
    }
}
