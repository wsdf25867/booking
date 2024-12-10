package io.ryan.booking.domain.outbox.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "outboxs")
class Outbox(

    val domain: String,
    val topic: String,
    val domainId: Long,
    val eventType: String,
    val payload: String,
    var status: OutboxStatus = OutboxStatus.PENDING,
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {
    fun sent() {
        status = OutboxStatus.SENT
    }

    fun processed() {
        status = OutboxStatus.PROCESSED
    }
}
