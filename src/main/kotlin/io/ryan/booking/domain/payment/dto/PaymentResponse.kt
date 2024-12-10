package io.ryan.booking.domain.payment.dto

import java.math.BigDecimal

data class PaymentResponse(
    val paymentId: Long,
    val paidAmount: BigDecimal,
) {

}
