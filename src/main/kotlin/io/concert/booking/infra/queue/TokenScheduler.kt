package io.concert.booking.infra.queue

import io.concert.booking.config.properties.SchedulerProperties
import io.concert.booking.domain.queue.TokenRepository
import io.concert.booking.domain.queue.TokenStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class TokenScheduler(
    private val schedulerProperties: SchedulerProperties,
    private val tokenRepository: TokenRepository,
) {

    @Scheduled(cron = "0 * * * * *")
    fun passTokenEveryMinute() {
        val passTarget =
            tokenRepository.findAllByStatusAndSize(TokenStatus.WAIT, schedulerProperties.passTokenSize)

        passTarget.forEach { it.passedAt(LocalDateTime.now()) }
    }

    @Scheduled(cron = "0 * * * * *")
    fun expiredTokenEveryMinute() {
        val targetTime = LocalDateTime.now().minusMinutes(10)
        tokenRepository.deleteByStatusAndUpdatedAtBefore(TokenStatus.PASS, targetTime)
    }

}