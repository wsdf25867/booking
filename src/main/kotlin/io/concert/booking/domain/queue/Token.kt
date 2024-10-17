package io.concert.booking.domain.queue

import io.concert.booking.support.jpa.DomainEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "queue")
class Token(
    val token: UUID = UUID.randomUUID(),
    val userId: Long,
    val concertId: Long,
    var status: TokenStatus = TokenStatus.WAIT,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
): DomainEntity() {

    fun passedAt(passDateTime: LocalDateTime) {
        val passableDateTime = createdAt.plusMinutes(5)
        check(passDateTime.isBefore(passableDateTime)) {
            "만료된 토큰입니다."
        }
        status = TokenStatus.PASS
    }

    fun isPass(): Boolean = status == TokenStatus.PASS
}

