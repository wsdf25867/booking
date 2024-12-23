package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.support.BaseEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ConstraintMode
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "booking")
class Booking(
    @Column(nullable = false)
    val userId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: BookingStatus = BookingStatus.TEMPORARILY_HELD,

    @Embedded
    val bookingSchedule: BookingSchedule,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "booking_seat",
        joinColumns = [JoinColumn(name = "booking_id")],
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT),
    )
    val bookingSeats: Set<BookingSeat>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) : BaseEntity<Booking>() {

    init {
        require(bookingSeats.isNotEmpty()) { "좌석은 필수" }

        registerEvent(BookingCreatedEvent(this))
    }

    fun seatIds(): Set<Long> = bookingSeats.map { it.seatId }.toSet()

    fun payed(currentTime: LocalDateTime = LocalDateTime.now()) {
        check(bookingSchedule.isPayableWith(currentTime)) { "확정 가능 시간이 지났습니다." }
        check(status == BookingStatus.TEMPORARILY_HELD) { "확정 가능 상태가 아닙니다." }

        status = BookingStatus.PAID

        registerEvent(BookingPaidEvent(this))
    }

    fun cancelled() {
        status = BookingStatus.CANCELLED
    }
}
