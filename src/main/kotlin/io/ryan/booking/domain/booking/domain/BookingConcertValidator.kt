package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.ConcertRepository
import io.ryan.booking.domain.concert.domain.SeatRepository
import org.springframework.stereotype.Component
import java.time.Clock

@Component
class BookingConcertValidator(
    private val seatRepository: SeatRepository,
    private val concertRepository: ConcertRepository,
    private val clock: Clock,
) : BookingValidator {

    override fun validate(booking: Booking) {

    }
}
