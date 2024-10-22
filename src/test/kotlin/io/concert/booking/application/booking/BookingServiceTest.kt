package io.concert.booking.application.booking

import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.domain.booking.BookingRepository
import io.concert.booking.domain.booking.FakeBookingRepository
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.concert.FakeConcertRepository
import io.concert.booking.domain.seat.FakeSeatRepository
import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatRepository
import io.concert.booking.domain.user.FakeUserRepository
import io.concert.booking.domain.user.User
import io.concert.booking.domain.user.UserRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class BookingServiceTest {

    private val seatRepository: SeatRepository = FakeSeatRepository()
    private val bookingRepository: BookingRepository = FakeBookingRepository()
    private val userRepository: UserRepository = FakeUserRepository()
    private val concertRepository: ConcertRepository = FakeConcertRepository()

    private val sut: BookingService = BookingService(
        seatRepository,
        bookingRepository,
        concertRepository,
        userRepository,
    )

    @Test
    fun `좌석 예약시 유저 정보가 없다면 IllegalArgumentException 발생`() {
        // given
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.create(BookingCreateDto(1, 1))
        }.withMessageContaining("유저 정보")
    }
    
    @Test
    fun `좌석 예약시 좌석 정보가 없다면 IllegalArgumentException 발생`() {
        // given
        val user = User("some name")
        userRepository.save(user)

        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.create(BookingCreateDto(1, 1))
        }.withMessageContaining("좌석 정보")
    }

    @Test
    fun `좌석 예약시 콘서트 정보가 없다면 IllegalArgumentException 발생`() {
        // given
        val user = User("some name")
        userRepository.save(user)

        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)

        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.create(BookingCreateDto(1, 1))
        }.withMessageContaining("콘서트 정보")
    }

    @Test
    fun `좌석 예약시 시간이 지난 콘서트라면 IllegalArgumentException 발생`() {
        // given
        val user = User("some name")
        userRepository.save(user)

        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)

        val concert = Concert("some concert", LocalDateTime.of(1995, 3, 26, 0, 0))
        concertRepository.save(concert)
        // when
        // then
        assertThatIllegalStateException().isThrownBy {
            sut.create(BookingCreateDto(1, 1, LocalDateTime.of(1995, 3, 26, 0, 0)))
        }.withMessageContaining("예약")
    }

    @Test
    fun `좌석 예약 성공`() {
        // given
        val user = User("some name")
        userRepository.save(user)

        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)

        val concert = Concert("some concert", LocalDateTime.of(1995, 3, 26, 0, 0))
        concertRepository.save(concert)

        // when
        val actual = sut.create(BookingCreateDto(1, 1, LocalDateTime.of(1995, 3, 25, 0, 0)))

        // then
        assertThat(actual).extracting("bookingId", "seatId", "userId")
            .containsExactly(1L, 1L, 1L)
    }
}
