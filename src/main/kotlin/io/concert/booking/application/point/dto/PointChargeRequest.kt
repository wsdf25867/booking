package io.concert.booking.application.point.dto

import java.math.BigDecimal

data class PointChargeRequest(
    val userId: Long,
    val amount: BigDecimal,
) {

}
