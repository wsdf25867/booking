package io.ryan.booking.domain.concert.domain

import io.ryan.booking.domain.booking.domain.Booking
import io.ryan.booking.domain.user.domain.User
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
    val place: String,
    val seatNumber: Int,
    val price: BigDecimal,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {
}
