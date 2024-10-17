package io.concert.booking.domain.queue

import java.util.*

class FakeTokenRepository : TokenRepository {

    private val store: MutableMap<Long, Token> = mutableMapOf()
    private var sequence: Long = 1

    override fun findByToken(token: UUID): Token? {
        return store.values.find { it.token == token }
    }

    override fun save(token: Token): Token {
        val toSave = Token(token = token.token, userId = token.userId, concertId = token.concertId, id = sequence++)
        store[toSave.id] = toSave
        return toSave
    }

    override fun countByTokenStatus(status: TokenStatus): Int {
        return store.values.count { it.status == status }
    }

    override fun countByStatusAndIdLessThan(status: TokenStatus, id: Long): Int {
        return store.values.count { it.status == status && it.id < id }
    }

    override fun findAllByStatusAndSize(status: TokenStatus, size: Int): List<Token> {
        return store.values
            .filter { it.status == status }
            .sortedBy { it.id }
            .take(size)
    }

}
