package io.ryan.booking.domain.point.dto

import java.math.BigDecimal
import kotlin.Long

data class PointChargeCommand(
    val userId: Long,
    val amount: BigDecimal,
) {

}
