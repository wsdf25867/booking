package io.concert.booking.application.queue

import io.concert.booking.application.queue.dto.TokenDto
import io.concert.booking.application.queue.dto.TokenQueueDto
import io.concert.booking.domain.concert.ConcertService
import io.concert.booking.domain.queue.TokenService
import io.concert.booking.domain.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class TokenApplicationService(
    private val tokenService: TokenService,
    private val userService: UserService,
    private val concertService: ConcertService,
) {

    @Transactional
    fun createToken(userId: Long, concertId: Long): TokenDto {
        val user = userService.get(userId)
        val concert = concertService.get(concertId)
        val token = tokenService.create(user.id, concert.id)

        return TokenDto(token.uuid, token.userId, token.concertId)
    }

    @Transactional(readOnly = true)
    fun getTokenQueueInfo(uuid: UUID): TokenQueueDto {
        val token = tokenService.getByUuid(uuid)
        val queueSize = tokenService.getWaitingQueueSize()
        val queueIndex = tokenService.getWaitingQueueIndex(token.id)

        return TokenQueueDto(token.uuid, queueSize, queueIndex)
    }

    @Transactional(readOnly = true)
    fun validateToken(uuid: UUID) {
        val token = tokenService.getByUuid(uuid)
        tokenService.validate(token)
    }
}
