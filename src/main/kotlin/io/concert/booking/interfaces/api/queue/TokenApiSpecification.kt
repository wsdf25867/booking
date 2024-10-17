package io.concert.booking.interfaces.api.queue

import io.concert.booking.interfaces.dto.queue.TokenGenerateRequest
import io.concert.booking.interfaces.dto.queue.TokenQueueResponse
import io.concert.booking.interfaces.dto.queue.TokenResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Tag(name = "Token")
interface TokenApiSpecification {

    @GetMapping("/{token}")
    fun getQueueInfo(token: String): TokenQueueResponse
    @PostMapping("/")
    fun generateToken(request: TokenGenerateRequest): TokenResponse
}
