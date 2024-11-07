package io.concert.booking.domain.booking

import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.user.User
import org.springframework.stereotype.Component

@Component
class BookingService(
    private val bookingRepository: BookingRepository
) {
    fun create(user: User, seat: Seat): Booking =
        seat.bookBy(user)
            .let { bookingRepository.save(it) }

}
