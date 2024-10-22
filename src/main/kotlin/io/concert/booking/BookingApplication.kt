package io.concert.booking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@EnableJpaAuditing
@EnableScheduling
@EnableConfigurationProperties
@SpringBootApplication
class BookingApplication

fun main(args: Array<String>) {
    runApplication<BookingApplication>(*args)
}
