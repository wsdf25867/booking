package io.concert.booking.domain.outbox

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OutboxRepository: JpaRepository<Outbox, Long> {
    @Query("select o from Outbox o where o.topic = :topic and o.domainId = :domainId")
    fun findByTopicAndDomainId(
        @Param("topic") topic: String,
        @Param("domainId") domainId: Long
    ): Outbox?

    fun findAllByTopicAndStatus(topic: String, status: OutboxStatus): List<Outbox>
}
