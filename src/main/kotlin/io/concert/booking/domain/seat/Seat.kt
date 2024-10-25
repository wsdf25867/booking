package io.concert.booking.domain.seat

import io.concert.booking.domain.booking.Booking
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "seats")
class Seat(
    val concertId: Long,
    val seatNumber: Int,
    val price: BigDecimal,
    var status: SeatStatus = SeatStatus.EMPTY,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {

    fun book(userId: Long): Booking {
        check(status == SeatStatus.EMPTY) { "이미 선택된 좌석입니다." }
        status = SeatStatus.OCCUPIED
        return Booking(
            userId = userId,
            seatId = id,
        )
    }

    fun empty() {
        status = SeatStatus.EMPTY
    }
}
