package io.ryan.booking.domain.concert.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "concert_schedule_seat")
class ConcertScheduleSeat(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_schedule_id", nullable = false)
    val concertSchedule: ConcertSchedule,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    val seat: Seat,
    @Enumerated(EnumType.STRING)
    var status: SeatStatus = SeatStatus.EMPTY,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {

    val price = seat.price

    fun empty() {
        status = SeatStatus.EMPTY
    }

    fun occupied() {
        status = SeatStatus.OCCUPIED
    }

    fun isEmpty(): Boolean = status == SeatStatus.EMPTY
}
