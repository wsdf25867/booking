package io.concert.booking.domain.seat

import io.concert.booking.domain.booking.Booking
import io.concert.booking.domain.user.User
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
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
    @Enumerated(EnumType.STRING) var status: SeatStatus = SeatStatus.EMPTY,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {

    fun empty() {
        status = SeatStatus.EMPTY
    }

    fun bookBy(user: User): Booking {
        check(status == SeatStatus.EMPTY) { "이미 선택된 좌석입니다." }
        status = SeatStatus.OCCUPIED
        return Booking(
            userId = user.id,
            seatId = id,
            price = price,
        )

    }

    fun occupied() {
        status = SeatStatus.OCCUPIED
    }

    fun isEmpty(): Boolean = status == SeatStatus.EMPTY
}
