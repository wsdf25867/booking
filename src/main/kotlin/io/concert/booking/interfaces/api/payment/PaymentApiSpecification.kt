package io.concert.booking.interfaces.api.payment

import io.concert.booking.interfaces.dto.payment.PaymentRequest
import io.concert.booking.interfaces.dto.payment.PaymentResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping

@Tag(name = "Payment")
interface PaymentApiSpecification {

    @PostMapping
    fun pay(request: PaymentRequest): PaymentResponse
}
