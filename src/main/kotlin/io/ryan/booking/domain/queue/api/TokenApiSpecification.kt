package io.ryan.booking.domain.queue.api

import io.ryan.booking.domain.queue.dto.TokenCreateRequest
import io.ryan.booking.domain.queue.dto.TokenQueueResponse
import io.ryan.booking.domain.queue.dto.TokenResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Tag(name = "Token")
interface TokenApiSpecification {

    @GetMapping("/{token}")
    fun getTokenQueueInfo(uuid: String): TokenQueueResponse
    @PostMapping("/")
    fun createToken(request: TokenCreateRequest): TokenResponse
}
