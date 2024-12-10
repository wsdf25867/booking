package io.concert.booking.application.queue.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime
import java.util.*

interface TokenRepository: JpaRepository<Token, Long> {

    @Query("select count(t) from Token t where t.status = :status")
    fun countByTokenStatus(status: TokenStatus): Int

    @Query("select count(t) from Token t where t.status = :status and t.id < :id")
    fun countByStatusAndIdLessThan(status: TokenStatus, id: Long): Int

    @Query("select t from Token t where t.status = :status order by t.id asc limit :size")
    fun findAllByStatusAndSize(status: TokenStatus, size: Int): List<Token>

    @Modifying
    @Query("delete from Token t where t.status = :status and t.updatedAt < :time")
    fun deleteByStatusAndUpdatedAtBefore(status: TokenStatus, time: LocalDateTime)
    fun findByUuid(uuid: UUID): Token?
}
