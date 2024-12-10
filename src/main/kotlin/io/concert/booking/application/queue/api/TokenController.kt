package io.concert.booking.application.queue.api

import io.concert.booking.application.queue.service.TokenFacade
import io.concert.booking.application.queue.dto.TokenCreateRequest
import io.concert.booking.application.queue.dto.TokenQueueResponse
import io.concert.booking.application.queue.dto.TokenResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/tokens")
class TokenController(
    private val tokenFacade: TokenFacade,
) : TokenApiSpecification {

    @PostMapping("/")
    override fun createToken(@RequestBody request: TokenCreateRequest): TokenResponse {
        val token = tokenFacade.createToken(request.userId, request.concertId)
        return TokenResponse(token.userId, token.uuid)
    }

    @GetMapping("/{token}")
    override fun getTokenQueueInfo(@PathVariable("token") uuid: String): TokenQueueResponse {
        val tokenInfo = tokenFacade.getTokenQueueInfo(UUID.fromString(uuid))
        return TokenQueueResponse(tokenInfo.queueIndex, tokenInfo.queueSize)
    }
}
