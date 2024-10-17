package io.concert.booking.domain.booking

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "bookings")
class Booking(
    val userId: Long,
    val seatId: Long,
    val bookingStatus: BookingStatus = BookingStatus.TEMPORARY,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {
}
