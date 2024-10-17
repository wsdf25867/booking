package io.concert.booking.application.payment.dto

import java.math.BigDecimal

data class PaymentDto(
    val paymentId: Long,
    val amount: BigDecimal,
) {

}
