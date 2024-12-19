package io.ryan.booking.domain.point.dto

import io.ryan.booking.domain.point.domain.Point
import java.math.BigDecimal

data class PointResponse(
    val userId: Long,
    var balance: BigDecimal = BigDecimal.ZERO,
) {
    companion object {
        fun from(point: Point): PointResponse {
            return PointResponse(
                userId = point.userId,
                balance = point.balance,
            )
        }
    }
}
