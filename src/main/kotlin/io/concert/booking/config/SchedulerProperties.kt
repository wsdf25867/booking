package io.concert.booking.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "scheduler")
data class SchedulerProperties(
    val passTokenSize: Int = 50,
) {
}
