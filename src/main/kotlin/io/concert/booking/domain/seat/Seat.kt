package io.concert.booking.domain.seat

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "seats")
class Seat(
    val concertId: Long,
    val seatNumber: Int,
    val price: BigDecimal,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {
}
