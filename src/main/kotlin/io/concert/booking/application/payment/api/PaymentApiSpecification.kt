package io.concert.booking.application.payment.api

import io.concert.booking.application.payment.dto.PaymentRequest
import io.concert.booking.application.payment.dto.PaymentResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping

@Tag(name = "Payment")
interface PaymentApiSpecification {

    @PostMapping
    fun pay(request: PaymentRequest): PaymentResponse
}
