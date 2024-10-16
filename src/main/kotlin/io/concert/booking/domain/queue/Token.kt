package io.concert.booking.domain.queue

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "queue")
class Token(
    val token: UUID = UUID.randomUUID(),
    val userId: Long,
    val concertId: Long,
    var expiredAt: LocalDateTime? = null,
    var status: TokenStatus = TokenStatus.WAIT,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {

    fun passedAt(passDateTime: LocalDateTime) {
        val passableDateTime = createdAt.plusMinutes(5)
        check(passDateTime.isBefore(passableDateTime)) {
            "만료된 토큰입니다."
        }
        status = TokenStatus.PASS
    }
}

