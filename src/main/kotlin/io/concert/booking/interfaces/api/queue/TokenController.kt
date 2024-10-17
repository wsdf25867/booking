package io.concert.booking.interfaces.api.queue

import io.concert.booking.application.queue.TokenService
import io.concert.booking.application.queue.dto.TokenGenerateParam
import io.concert.booking.application.queue.dto.TokenSearchCond
import io.concert.booking.interfaces.dto.queue.TokenGenerateRequest
import io.concert.booking.interfaces.dto.queue.TokenQueueResponse
import io.concert.booking.interfaces.dto.queue.TokenResponse
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/tokens")
class TokenController(
    private val tokenService: TokenService,
) : TokenApiSpecification {

    @PostMapping("/")
    override fun generateToken(@RequestBody request: TokenGenerateRequest): TokenResponse {
        val token = tokenService.generateToken(TokenGenerateParam(request.userId, request.concertId))
        return TokenResponse(token.userId, token.token)
    }

    @GetMapping("/{token}")
    override fun getQueueInfo(@PathVariable("token") token: String): TokenQueueResponse {
        val tokenInfo = tokenService.findToken(TokenSearchCond(UUID.fromString(token)))
        return TokenQueueResponse(tokenInfo.queueIndex, tokenInfo.queueSize)
    }
}
