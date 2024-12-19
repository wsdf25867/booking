package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.ConcertSchedule
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.Duration
import java.time.LocalDateTime

@Embeddable
class BookingConcertSchedule(

    concertSchedule: ConcertSchedule,
    @Column
    val bookedAt: LocalDateTime = LocalDateTime.now(),
) {

    @Column(nullable = false)
    val concertScheduleId: Long = concertSchedule.id

    @Column(nullable = false)
    val confirmableDateTime: LocalDateTime = bookedAt.plus(CONFIRMABLE_DURATION)

    init {
        check(concertSchedule.isBookableWith(bookedAt)) { "예약 가능 시간이 아닙니다." }
    }

    fun isConfirmableWith(currentTime: LocalDateTime): Boolean =
        currentTime <= confirmableDateTime

    companion object {
        private val CONFIRMABLE_DURATION = Duration.ofMinutes(5L)
    }
}
