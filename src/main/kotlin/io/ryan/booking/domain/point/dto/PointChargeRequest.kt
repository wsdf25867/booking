package io.ryan.booking.domain.point.dto

import java.math.BigDecimal
import kotlin.Long

data class PointChargeRequest(
    val userId: Long,
    val amount: BigDecimal,
) {

    fun toServiceRequest(): PointChargeServiceRequest {
        return PointChargeServiceRequest(userId, amount)
    }
}
