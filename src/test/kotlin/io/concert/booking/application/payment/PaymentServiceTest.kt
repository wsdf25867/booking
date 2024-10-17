package io.concert.booking.application.payment

import io.concert.booking.application.payment.dto.PaymentCreateDto
import io.concert.booking.domain.booking.Booking
import io.concert.booking.domain.booking.BookingRepository
import io.concert.booking.domain.booking.FakeBookingRepository
import io.concert.booking.domain.payment.FakePaymentRepository
import io.concert.booking.domain.payment.PaymentRepository
import io.concert.booking.domain.point.*
import io.concert.booking.domain.seat.FakeSeatRepository
import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatRepository
import io.concert.booking.domain.user.FakeUserRepository
import io.concert.booking.domain.user.User
import io.concert.booking.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class PaymentServiceTest {

    private val seatRepository: SeatRepository = FakeSeatRepository()
    private val userRepository: UserRepository = FakeUserRepository()
    private val bookingRepository: BookingRepository = FakeBookingRepository()
    private val paymentRepository: PaymentRepository = FakePaymentRepository()
    private val pointRepository: PointRepository = FakePointRepository()
    private val pointHistoryRepository: PointHistoryRepository = FakePointHistoryRepository()
    private val sut: PaymentService = PaymentService(
        paymentRepository,
        bookingRepository,
        userRepository,
        seatRepository,
        pointRepository,
        pointHistoryRepository,
    )

    @Test
    fun `결제 생성시 좌석정보가 없으면 IllegalArgumentException 발생`() {
        // given
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.create(PaymentCreateDto(1L, 1L, 1L, 1L))
        }.withMessageContaining("좌석 정보")
    }

    @Test
    fun `결제 생성시 유저정보가 없으면 IllegalArgumentException 발생`() {
        // given
        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)
        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.create(PaymentCreateDto(1L, 1L, 1L, 1L))
        }.withMessageContaining("유저 정보")
    }

    @Test
    fun `결제 생성시 예약정보가 없으면 IllegalArgumentException 발생`() {
        // given
        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)

        val user = User("name")
        userRepository.save(user)

        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.create(PaymentCreateDto(1L, 1L, 1L, 1L))
        }.withMessageContaining("예약 정보")
    }

    @Test
    fun `결제 생성시 포인트정보가 없으면 IllegalArgumentException 발생`() {
        // given
        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)

        val user = User("name")
        userRepository.save(user)

        val booking = Booking(1L, 1L)
        bookingRepository.save(booking)

        // when
        // then
        assertThatIllegalArgumentException().isThrownBy {
            sut.create(PaymentCreateDto(1L, 1L, 1L, 1L))
        }.withMessageContaining("포인트 정보")
    }

    @Test
    fun `결제 생성`() {
        // given
        val seat = Seat(1L, 1, 100.toBigDecimal())
        seatRepository.save(seat)

        val user = User("name")
        userRepository.save(user)

        val booking = Booking(1L, 1L)
        bookingRepository.save(booking)

        val point = Point(1L, 100.toBigDecimal())
        pointRepository.save(point)

        // when
        val paymentDto = sut.create(PaymentCreateDto(1L, 1L, 1L, 1L))

        // then
        assertThat(paymentDto).extracting("paymentId", "amount")
            .containsExactly(1L, 100.toBigDecimal())
    }
}
