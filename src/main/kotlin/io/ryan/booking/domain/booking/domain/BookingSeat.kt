package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.ConcertScheduleSeat
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
class BookingSeat private constructor(

    @Column(name = "concert_schedule_seat_id")
    val seatId: Long,

    @Column(name = "price")
    val price: BigDecimal,
) {

    companion object {
        operator fun invoke(seat: ConcertScheduleSeat): BookingSeat {
            return create(seat)
        }

        fun create(seat: ConcertScheduleSeat): BookingSeat {
            check(seat.isEmpty()) { "이미 예약된 좌석입니다. scheduleSeatId: ${seat.id}" }
            return BookingSeat(seat.id, seat.price)
        }
    }
}
