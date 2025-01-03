package io.ryan.booking.domain.queue.application

import io.ryan.booking.domain.cache.service.CacheService
import io.ryan.booking.domain.queue.dto.TokenDto
import io.ryan.booking.domain.queue.dto.TokenQueueDto
import io.ryan.booking.domain.concert.application.ConcertQueryService
import io.ryan.booking.domain.queue.domain.Token
import io.ryan.booking.domain.queue.domain.TokenService
import io.ryan.booking.domain.user.application.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class TokenFacade(
    private val tokenService: TokenService,
    private val userService: UserService,
    private val concertService: ConcertQueryService,
    private val cacheService: CacheService,
) {

    @Transactional
    fun createToken(userId: Long, concertId: Long): TokenDto {
        val user = userService.get(userId)
        val concert = concertService.get(concertId)
        val token = tokenService.create(user.id, concert.id)
        cacheService.set(token.uuid, token, 5, TimeUnit.MINUTES, Token::class.java)
        cacheService.increase("waitingQueue::size")

        return TokenDto(token.uuid, token.userId, token.concertId)
    }

    @Transactional(readOnly = true)
    fun getTokenQueueInfo(uuid: UUID): TokenQueueDto {
        val token = cacheService.get(uuid, Token::class.java)
            ?: tokenService.getByUuid(uuid)
        val queueSize = cacheService.get("waitingQueue::size", Int::class.java)
            ?: tokenService.getWaitingQueueSize()

        val queueIndex = token.currentQueueIndex()

        return TokenQueueDto(token.uuid, queueSize, queueIndex)
    }

    @Transactional(readOnly = true)
    fun canPass(uuid: UUID): Boolean {
        val token = tokenService.getByUuid(uuid)
        return tokenService.isPass(token)
    }
}
