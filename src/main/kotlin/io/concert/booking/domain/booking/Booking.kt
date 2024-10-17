package io.concert.booking.domain.booking

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "bookings")
class Booking(
    val userId: Long,
    val seatId: Long,
    var status: BookingStatus = BookingStatus.TEMPORARY,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {

    fun confirmedAt(dateTime: LocalDateTime) {
        check(createdAt.plusMinutes(5) > dateTime) { "예약 가능 시간이 지났습니다." }
        status = BookingStatus.CONFIRMED
    }

    fun cancelled() {
        status = BookingStatus.CANCELLED
    }
}
