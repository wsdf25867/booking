package io.concert.booking.domain.queue

import java.util.UUID

interface TokenRepository {

    fun findByToken(token: UUID): Token?
    fun save(token: Token): Token
    fun countByTokenStatus(status: TokenStatus): Int
    fun countByStatusAndIdLessThan(status: TokenStatus, id: Long): Int
    fun findAllByStatusAndSize(status: TokenStatus, size: Int): List<Token>
}
