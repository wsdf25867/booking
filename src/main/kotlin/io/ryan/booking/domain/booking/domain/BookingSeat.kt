package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.ConcertScheduleSeat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "booking_seat")
class BookingSeat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    seat: ConcertScheduleSeat,
) {
    @Column(nullable = false)
    val seatId: Long = seat.id

    @Column(nullable = false)
    val price: BigDecimal = seat.price

    init {
        check(seat.isEmpty()) { "이미 선택된 좌석 입니다. seatId: ${seat.id}" }
    }
}
