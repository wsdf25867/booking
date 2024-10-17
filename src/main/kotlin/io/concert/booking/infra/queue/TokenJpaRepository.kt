package io.concert.booking.infra.queue

import io.concert.booking.domain.queue.Token
import io.concert.booking.domain.queue.TokenRepository
import io.concert.booking.domain.queue.TokenStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TokenJpaRepository : JpaRepository<Token, Long>, TokenRepository {
    @Query("select count(t) from Token t where t.status = :status")
    override fun countByTokenStatus(@Param("status") status: TokenStatus): Int

    @Query("select count(t) from Token t where t.status = :status and t.id < :id")
    override fun countByStatusAndIdLessThan(@Param("status") status: TokenStatus, @Param("id") id: Long): Int

    @Query("select t from Token t where t.status = :status order by t.id asc limit :size")
    override fun findAllByStatusAndSize(status: TokenStatus, size: Int): List<Token>
}
