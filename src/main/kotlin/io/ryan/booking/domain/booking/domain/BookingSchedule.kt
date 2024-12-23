package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.ConcertSchedule
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.Duration
import java.time.LocalDateTime

@Embeddable
class BookingSchedule(
    concertSchedule: ConcertSchedule,
    @Column(nullable = false)
    val bookedAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = false)
    val payableDateTime: LocalDateTime = bookedAt.plus(PAYABLE_DURATION)
) {
    @Column(nullable = false)
    val concertScheduleId: Long = concertSchedule.id

    fun isPayableWith(currentTime: LocalDateTime): Boolean =
        payableDateTime <= currentTime

    init {
        check(concertSchedule.isBookableWith(bookedAt)) { "예약 가능 시간이 아닙니다." }
    }

    companion object {
        private val PAYABLE_DURATION = Duration.ofMinutes(5L)
    }
}
