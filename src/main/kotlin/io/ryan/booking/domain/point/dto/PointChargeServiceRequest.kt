package io.ryan.booking.domain.point.dto

import java.math.BigDecimal
import kotlin.Long

data class PointChargeServiceRequest(
    val userId: Long,
    val amount: BigDecimal,
) {

}
