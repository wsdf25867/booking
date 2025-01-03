package io.ryan.booking.domain.outbox.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OutboxRepository: JpaRepository<Outbox, Long> {
    @Query("select o from Outbox o where o.topic = :topic and o.domainId = :domainId")
    fun findByTopicAndDomainId(
        @Param("topic") topic: String,
        @Param("domainId") domainId: Long
    ): Outbox?

    fun findAllByStatus(status: OutboxStatus): List<Outbox>
}
