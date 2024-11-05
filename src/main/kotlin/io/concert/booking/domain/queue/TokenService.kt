package io.concert.booking.domain.queue

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class TokenService(
    private val tokenRepository: TokenRepository,
) {

    @Transactional
     fun create(userId: Long, concertId: Long): Token {
        val token: Token = Token.create(userId, concertId)
        return tokenRepository.save(token)
    }

    @Transactional(readOnly = true)
     fun getByUuid(uuid: UUID): Token {
        val token = tokenRepository.findByUuid(uuid)
        return requireNotNull(token) {
            "토큰 정보가 존재하지 않습니다."
        }
    }

    @Transactional(readOnly = true)
     fun getWaitingQueueSize(): Int =
        tokenRepository.countByTokenStatus(TokenStatus.WAIT)

    @Transactional(readOnly = true)
     fun getWaitingQueueIndex(id: Long): Int =
        tokenRepository.countByStatusAndIdLessThan(TokenStatus.WAIT, id)

     fun validate(token: Token) {
        check(token.isPass()) { "통과되지 않은 토큰" }
    }
}
