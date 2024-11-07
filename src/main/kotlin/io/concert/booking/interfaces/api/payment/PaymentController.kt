package io.concert.booking.interfaces.api.payment

import io.concert.booking.application.payment.PaymentFacade
import io.concert.booking.application.payment.dto.PaymentCreateDto
import io.concert.booking.interfaces.dto.payment.PaymentRequest
import io.concert.booking.interfaces.dto.payment.PaymentResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/payments")
class PaymentController(
    private val paymentFacade: PaymentFacade
): PaymentApiSpecification {

    @PostMapping
    override fun pay(
        @RequestBody request: PaymentRequest,
    ): PaymentResponse {
        val paymentDto = paymentFacade.create(
            PaymentCreateDto(
                request.userId,
                request.concertId,
                request.seatId,
                request.bookingId
            )
        )
        return PaymentResponse(paymentDto.paymentId, paymentDto.amount)
    }
}
