package io.concert.booking.application.payment

import io.concert.booking.application.payment.dto.PaymentCreateDto
import io.concert.booking.application.payment.dto.PaymentDto
import io.concert.booking.domain.booking.BookingRepository
import io.concert.booking.domain.payment.Payment
import io.concert.booking.domain.payment.PaymentRepository
import io.concert.booking.domain.point.PointHistoryRepository
import io.concert.booking.domain.point.PointRepository
import io.concert.booking.domain.seat.SeatRepository
import io.concert.booking.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PaymentApplicationService(
    private val paymentRepository: PaymentRepository,
    private val bookingRepository: BookingRepository,
    private val userRepository: UserRepository,
    private val seatRepository: SeatRepository,
    private val pointRepository: PointRepository,
    private val pointHistoryRepository: PointHistoryRepository,
) {
    fun create(dto: PaymentCreateDto): PaymentDto {
        val seat = requireNotNull(seatRepository.findById(dto.seatId)) { "좌석 정보가 없습니다." }
        val user = requireNotNull(userRepository.findById(dto.userId)) { "유저 정보가 없습니다." }
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
