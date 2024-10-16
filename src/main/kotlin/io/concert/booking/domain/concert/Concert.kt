package io.concert.booking.domain.concert

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "concerts")
class Concert(
    val name: String,
    val date: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {

}
