package io.ryan.booking.domain.concert.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "concert_schedule")
class ConcertSchedule(
    @ManyToOne(fetch = FetchType.LAZY)
    val concert: Concert,
    val concertDateTime: LocalDateTime = LocalDateTime.now(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {

    fun isBookableWith(currentDateTime: LocalDateTime): Boolean {
        return currentDateTime <= concertDateTime
    }
}
