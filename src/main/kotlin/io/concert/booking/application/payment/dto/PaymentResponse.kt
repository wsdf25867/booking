package io.concert.booking.application.payment.dto

import java.math.BigDecimal

data class PaymentResponse(
    val paymentId: Long,
    val paidAmount: BigDecimal,
) {

}
