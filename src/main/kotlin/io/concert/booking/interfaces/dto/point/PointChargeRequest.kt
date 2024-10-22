package io.concert.booking.interfaces.dto.point

import java.math.BigDecimal

data class PointChargeRequest(
    val userId: Long,
    val amount: BigDecimal,
) {

}
