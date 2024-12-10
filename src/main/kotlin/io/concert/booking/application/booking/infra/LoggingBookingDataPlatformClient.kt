package io.concert.booking.application.booking.infra

import io.concert.booking.application.booking.service.BookingDataPlatformClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class LoggingBookingDataPlatformClient(
    private val log: Logger = LoggerFactory.getLogger(LoggingBookingDataPlatformClient::class.java)
): BookingDataPlatformClient {
    override fun send(id: Long) {
        log.info("complete booking id: $id")
    }
}
