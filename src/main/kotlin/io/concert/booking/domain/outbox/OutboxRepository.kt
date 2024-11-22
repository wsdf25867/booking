package io.concert.booking.domain.outbox

import org.springframework.data.jpa.repository.JpaRepository

interface OutboxRepository: JpaRepository<Outbox, Long> {
}
