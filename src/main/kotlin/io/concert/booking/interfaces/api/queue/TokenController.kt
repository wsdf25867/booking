package io.concert.booking.interfaces.api.queue

import io.concert.booking.application.queue.TokenApplicationService
import io.concert.booking.interfaces.dto.queue.TokenCreateRequest
import io.concert.booking.interfaces.dto.queue.TokenQueueResponse
import io.concert.booking.interfaces.dto.queue.TokenResponse
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
    private val tokenApplicationService: TokenApplicationService,
) : TokenApiSpecification {

    @PostMapping("/")
    override fun createToken(@RequestBody request: TokenCreateRequest): TokenResponse {
        val token = tokenApplicationService.createToken(request.userId, request.concertId)
        return TokenResponse(token.userId, token.uuid)
    }

    @GetMapping("/{token}")
    override fun getTokenQueueInfo(@PathVariable("token") uuid: String): TokenQueueResponse {
        val tokenInfo = tokenApplicationService.getTokenQueueInfo(UUID.fromString(uuid))
        return TokenQueueResponse(tokenInfo.queueIndex, tokenInfo.queueSize)
    }
}
