package io.concert.booking.domain.queue

import io.concert.booking.domain.support.DomainEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlin.time.toKotlinDuration

@Entity
@Table(name = "queue")
class Token(
    val uuid: UUID = UUID.randomUUID(),
    val userId: Long,
    val concertId: Long,
    var status: TokenStatus = TokenStatus.WAIT,
    var issuedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) : DomainEntity() {

    fun passedAt(passDateTime: LocalDateTime) {
        val duration = Duration.between(issuedAt, passDateTime)
            .toKotlinDuration()
            .absoluteValue
        val fiveMinutes = 5.toDuration(DurationUnit.MINUTES)
        check(duration <= fiveMinutes) {
            "만료된 토큰입니다."
        }
        status = TokenStatus.PASS
    }

    fun isPass(): Boolean = status == TokenStatus.PASS


    companion object {
        fun create(userId: Long, concertId: Long): Token =
            Token(userId = userId, concertId = concertId)

    }
}

