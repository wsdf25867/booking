package io.concert.booking.interfaces.api.queue

import io.concert.booking.interfaces.dto.queue.TokenQueueResponse
import io.concert.booking.interfaces.dto.queue.TokenGenerateRequest
import io.concert.booking.interfaces.dto.queue.TokenResponse
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/api/v1/tokens")
class TokenController {

    @PostMapping("/")
    fun generateToken(@RequestBody request: TokenGenerateRequest): TokenResponse {
        return TokenResponse(1, UUID.randomUUID(), LocalDateTime.now())
    }

    @GetMapping("/{token}")
    fun getQueueInfo(@PathVariable("token") token: String): TokenQueueResponse {
        return TokenQueueResponse(12, 20)
    }
}
