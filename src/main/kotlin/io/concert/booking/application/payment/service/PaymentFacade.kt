package io.concert.booking.application.payment.service

import io.concert.booking.application.payment.dto.PaymentCreateDto
import io.concert.booking.application.payment.dto.PaymentDto
import io.concert.booking.application.booking.domain.BookingRepository
import io.concert.booking.application.payment.domain.Payment
import io.concert.booking.application.payment.domain.PaymentRepository
import io.concert.booking.application.point.domain.PointHistoryRepository
import io.concert.booking.application.point.domain.PointRepository
import io.concert.booking.application.seat.domain.SeatRepository
import io.concert.booking.application.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PaymentFacade(
    private val paymentRepository: PaymentRepository,
    private val bookingRepository: BookingRepository,
    private val userRepository: UserRepository,
    private val seatRepository: SeatRepository,
    private val pointRepository: PointRepository,
    private val pointHistoryRepository: PointHistoryRepository,
) {
    @Transactional
    fun create(dto: PaymentCreateDto): PaymentDto {
        val seat = requireNotNull(seatRepository.findByIdOrNull(dto.seatId)) { "좌석 정보가 없습니다." }
        val user = requireNotNull(userRepository.findByIdOrNull(dto.userId)) { "유저 정보가 없습니다." }
        val booking = requireNotNull(bookingRepository.findByIdWithLock(dto.bookingId)) { "예약 정보가 없습니다." }
        val point = requireNotNull(pointRepository.findByUserIdWithLock(user.id)) { "포인트 정보가 없습니다." }

        val history = point.use(seat.price)
        pointHistoryRepository.save(history)

        booking.confirmedAt(dto.paidAt)

        val payment = Payment(booking.id, seat.price)
        val saved = paymentRepository.save(payment)

        return PaymentDto(saved.id, saved.amount)
    }
}
