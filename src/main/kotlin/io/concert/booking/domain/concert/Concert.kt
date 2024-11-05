package io.concert.booking.domain.concert

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "concerts")
class Concert(
    val name: String,
    val date: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {

    fun isBookableAt(dateTime: LocalDateTime): Boolean {
        return dateTime <= date
    }

}
