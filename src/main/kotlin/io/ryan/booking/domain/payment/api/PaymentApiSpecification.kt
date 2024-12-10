package io.ryan.booking.domain.payment.api

import io.ryan.booking.domain.payment.dto.PaymentRequest
import io.ryan.booking.domain.payment.dto.PaymentResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping

@Tag(name = "Payment")
interface PaymentApiSpecification {

    @PostMapping
    fun pay(request: PaymentRequest): PaymentResponse
}
