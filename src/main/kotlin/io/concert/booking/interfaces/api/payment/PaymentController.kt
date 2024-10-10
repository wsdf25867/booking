package io.concert.booking.interfaces.api.payment

import io.concert.booking.interfaces.dto.payment.PaymentRequest
import io.concert.booking.interfaces.dto.payment.PaymentResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/payments")
class PaymentController {

    @PostMapping
    fun pay(
        @RequestBody request: PaymentRequest,
    ): PaymentResponse {
        return PaymentResponse(1, BigDecimal(100), BigDecimal.ZERO)
    }
}
