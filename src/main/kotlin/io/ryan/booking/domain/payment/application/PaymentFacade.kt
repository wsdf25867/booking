package io.ryan.booking.domain.payment.application

import io.ryan.booking.domain.payment.dto.PaymentCreateDto
import io.ryan.booking.domain.payment.dto.PaymentDto
import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.payment.domain.Payment
import io.ryan.booking.domain.payment.domain.PaymentRepository
import io.ryan.booking.domain.point.domain.PointHistoryRepository
import io.ryan.booking.domain.point.domain.PointRepository
import io.ryan.booking.domain.concert.domain.SeatRepository
import io.ryan.booking.domain.user.domain.UserRepository
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

        booking.confirmWith(dto.paidAt)

        val payment = Payment(booking.id, seat.price)
        val saved = paymentRepository.save(payment)

        return PaymentDto(saved.id, saved.amount)
    }
}
