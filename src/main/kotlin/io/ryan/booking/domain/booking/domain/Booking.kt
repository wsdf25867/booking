package io.ryan.booking.domain.booking.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.domain.AbstractAggregateRoot
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime
import kotlin.time.toKotlinDuration

@Entity
@Table(name = "bookings")
class Booking(
    val userId: Long,
    val seatId: Long,
    val price: BigDecimal,
    var status: BookingStatus = BookingStatus.NONE,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
): AbstractAggregateRoot<Booking>() {

    fun confirmedAt(dateTime: LocalDateTime) {
        val duration = Duration.between(createdAt, dateTime)
            .toKotlinDuration()
            .absoluteValue
        val fiveMinute = Duration.ofMinutes(5).toKotlinDuration()
        check(duration <= fiveMinute) { "예약 가능 시간이 지났습니다." }
        status = BookingStatus.CONFIRMED
    }

    fun cancelled() {
        status = BookingStatus.CANCELLED
    }

    fun heldTemporarily() {
        status = BookingStatus.TEMPORARILY_HELD
        registerEvent(BookingCreatedEvent(this))
    }
}
