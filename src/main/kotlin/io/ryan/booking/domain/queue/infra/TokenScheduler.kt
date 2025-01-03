package io.ryan.booking.domain.queue.infra

import io.ryan.booking.domain.queue.domain.TokenRepository
import io.ryan.booking.domain.queue.domain.TokenStatus
import io.ryan.booking.config.SchedulerProperties
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Component
@Transactional
class TokenScheduler(
    private val schedulerProperties: SchedulerProperties,
    private val tokenRepository: TokenRepository,
) {

    @Scheduled(cron = "0 * * * * *")
    fun passTokenEveryMinute() {
        val passTarget =
            tokenRepository.findAllByStatusAndSize(TokenStatus.WAIT, schedulerProperties.passTokenSize)

        try {
            passTarget.forEach { it.passedAt(LocalDateTime.now()) }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Scheduled(cron = "0 * * * * *")
    fun expiredTokenEveryMinute() {
        val targetTime = LocalDateTime.now().minusMinutes(10)
        tokenRepository.deleteByStatusAndUpdatedAtBefore(TokenStatus.PASS, targetTime)
    }

}
