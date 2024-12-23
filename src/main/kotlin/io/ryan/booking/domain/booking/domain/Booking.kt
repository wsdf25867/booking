package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.ConcertSchedule
import io.ryan.booking.domain.concert.domain.ConcertScheduleSeat
import io.ryan.booking.domain.support.BaseEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ConstraintMode
import jakarta.persistence.ElementCollection
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
import java.time.Duration
import java.time.LocalDateTime

@Entity
@Table(name = "booking")
class Booking private constructor(
    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val bookedAt: LocalDateTime,

    @Column(nullable = false)
    val concertScheduleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: BookingStatus = BookingStatus.TEMPORARILY_HELD,

    @Column(nullable = false)
    private val payableDateTime: LocalDateTime = bookedAt.plus(CONFIRMABLE_DURATION),

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "booking_seat",
        joinColumns = [JoinColumn(name = "booking_id")],
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    val bookingSeats: Set<BookingSeat>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) : BaseEntity<Booking>() {

    fun seatIds(): Set<Long> = bookingSeats.map { it.seatId }.toSet()

    fun payed(currentTime: LocalDateTime = LocalDateTime.now()) {
        require(payableDateTime <= currentTime) { "확정 가능 시간이 지났습니다." }
        check(status == BookingStatus.TEMPORARILY_HELD) { "확정 가능 상태가 아닙니다." }
        status = BookingStatus.PAID
    }

    fun cancelled() {
        status = BookingStatus.CANCELLED
    }

    companion object {
        private val CONFIRMABLE_DURATION = Duration.ofMinutes(5L)

        operator fun invoke(
            userId: Long,
            schedule: ConcertSchedule,
            seats: List<ConcertScheduleSeat>,
            bookedAt: LocalDateTime = LocalDateTime.now(),
        ): Booking {
            return create(userId, schedule, seats, bookedAt)
        }

        fun create(
            userId: Long,
            schedule: ConcertSchedule,
            seats: List<ConcertScheduleSeat>,
            bookedAt: LocalDateTime = LocalDateTime.now()
        ): Booking {

            check(schedule.isBookableWith(bookedAt)) { "예약 가능 시간이 아닙니다." }
            check(seats.isNotEmpty()) { "좌석 선택 필수" }

            val bookingSeats = seats.map { BookingSeat(it) }.toSet()

            return Booking(
                userId = userId,
                bookedAt = bookedAt,
                concertScheduleId = schedule.id,
                bookingSeats = bookingSeats
            )
        }
    }
}
