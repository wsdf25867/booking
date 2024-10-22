package io.concert.booking.interfaces.dto.payment

import java.math.BigDecimal

data class PaymentResponse(
    val paymentId: Long,
    val paidAmount: BigDecimal,
    val balance: BigDecimal,
) {

}
