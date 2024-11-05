package io.concert.booking.application.booking

import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.application.booking.dto.BookingDto
import io.concert.booking.domain.booking.BookingRepository
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.queue.TokenRepository
import io.concert.booking.domain.seat.SeatRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class BookingApplicationService(
    private val seatRepository: SeatRepository,
    private val bookingRepository: BookingRepository,
    private val concertRepository: ConcertRepository,
    private val tokenRepository: TokenRepository,
) {

    fun create(dto: BookingCreateDto): BookingDto {
        val user = requireNotNull(tokenRepository.findByUuid(UUID.fromString(dto.token))) { "유저 정보가 없습니다." }
        val seat = requireNotNull(seatRepository.findByIdWithLock(dto.seatId)) { "좌석 정보가 없습니다." }
        val concert = requireNotNull(concertRepository.findById(seat.concertId)) { "콘서트 정보가 없습니다." }

        if (concert.isNotBookableAt(dto.bookedAt)) {
            throw IllegalStateException("예약 불가능한 콘서트")
        }

        val booking = seat.book(user.id)
        val saved = bookingRepository.save(booking)
        return BookingDto.from(saved)
    }

}
