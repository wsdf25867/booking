package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.ConcertSchedule
import io.ryan.booking.domain.concert.domain.ConcertScheduleSeat
import io.ryan.booking.domain.support.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "booking")
class Booking private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val userId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: BookingStatus = BookingStatus.TEMPORARILY_HELD,

    @Embedded
    @Column(nullable = false)
    private var bookingConcertSchedule: BookingConcertSchedule,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "booking_id")
    private var bookingSeats: List<BookingSeat>

) : BaseEntity<Booking>() {

    init {
        require(bookingSeats.isNotEmpty()) { "좌석은 필수" }
        registerEvent(BookingCreatedEvent(this))
    }

    fun confirmWith(currentTime: LocalDateTime = LocalDateTime.now()) {
        require(bookingConcertSchedule.isConfirmableWith(currentTime)) { "확정 가능 시간이 지났습니다." }
        check(status == BookingStatus.TEMPORARILY_HELD) { "확정 가능 상태가 아닙니다." }
        status = BookingStatus.CONFIRMED
    }

    fun cancelled() {
        status = BookingStatus.CANCELLED
    }

    companion object {
        operator fun invoke(
            userId: Long,
            bookedAt: LocalDateTime,
            concertSchedule: ConcertSchedule,
            seats: List<ConcertScheduleSeat>
        ): Booking {
            val bookingConcertSchedule = BookingConcertSchedule(concertSchedule, bookedAt)
            val bookingSeats = seats.map { BookingSeat(seat = it) }
            return Booking(
                userId = userId,
                bookingConcertSchedule = bookingConcertSchedule,
                bookingSeats = bookingSeats,
            )
        }
    }
}
