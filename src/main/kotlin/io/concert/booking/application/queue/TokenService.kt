package io.concert.booking.application.queue

import io.concert.booking.application.queue.dto.TokenGenerateParam
import io.concert.booking.application.queue.dto.TokenGenerateResponse
import io.concert.booking.application.queue.dto.TokenSearchCond
import io.concert.booking.application.queue.dto.TokenSearchResponse
import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.queue.Token
import io.concert.booking.domain.user.UserRepository
import io.concert.booking.domain.queue.TokenRepository
import io.concert.booking.domain.queue.TokenStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TokenService(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository,
    private val concertRepository: ConcertRepository,
) {

    fun generateToken(param: TokenGenerateParam): TokenGenerateResponse {
        val user = requireNotNull(userRepository.findById(param.userId)) {
            "유저 정보가 존재하지 않습니다"
        }
        val concert = requireNotNull(concertRepository.findById(param.concertId)) {
            "콘서트 정보가 존재하지 않습니다"
        }

        val token = Token(userId = user.id, concertId = concert.id)
        val saved = tokenRepository.save(token)

        return TokenGenerateResponse(saved.token, saved.userId, saved.concertId)
    }

    @Transactional(readOnly = true)
    fun findToken(param: TokenSearchCond): TokenSearchResponse {
        val token = requireNotNull(tokenRepository.findByToken(param.token)) {
            "토큰 정보가 존재하지 않습니다."
        }
        val queueSize = tokenRepository.countByTokenStatus(TokenStatus.WAIT)
        val queueIndex = tokenRepository.countByStatusAndIdLessThan(TokenStatus.WAIT, token.id)

        return TokenSearchResponse( token.token, queueIndex, queueSize)
    }



}
