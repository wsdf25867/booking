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
    val issuedAt: LocalDateTime = LocalDateTime.now(),
    val requestSequence: Int = 0,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) : DomainEntity() {

    val passableAt: LocalDateTime =
        issuedAt.plusSeconds((INTERVAL_SECONDS * ((requestSequence - 1) / PASS_PER_INTERVAL)))

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

    fun isPass(): Boolean = LocalDateTime.now() <= passableAt

    fun currentQueueIndex(): Int {
        val timeGap = Duration.between(LocalDateTime.now(), issuedAt).seconds.toInt()
        return (requestSequence - (timeGap * PASS_PER_SECONDS)).toInt()

    }

    companion object {
        const val INTERVAL_SECONDS: Long = 10
        const val PASS_PER_INTERVAL: Long = 50
        const val PASS_PER_SECONDS: Long = 5
    }
}

