package io.concert.booking.application.queue.domain

import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenService(
    private val tokenRepository: TokenRepository,
) {

     fun create(userId: Long, concertId: Long): Token {
        val token = Token(userId = userId, concertId = concertId)
        return tokenRepository.save(token)
    }

     fun getByUuid(uuid: UUID): Token {
        val token = tokenRepository.findByUuid(uuid)
        return requireNotNull(token) {
            "토큰 정보가 존재하지 않습니다."
        }
    }

     fun getWaitingQueueSize(): Int =
        tokenRepository.countByTokenStatus(TokenStatus.WAIT)

     fun getWaitingQueueIndex(id: Long): Int =
        tokenRepository.countByStatusAndIdLessThan(TokenStatus.WAIT, id)

     fun validate(token: Token) {
        check(token.isPass()) { "통과되지 않은 토큰" }
    }

    fun isPass(token: Token): Boolean {
        return token.isPass()
    }
}
