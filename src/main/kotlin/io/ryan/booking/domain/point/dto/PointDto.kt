package io.ryan.booking.domain.point.dto

import io.ryan.booking.domain.point.domain.Point
import java.math.BigDecimal

data class PointDto(
    val userId: Long,
    val balance: BigDecimal,
) {
    companion object {
        fun from(point: Point) = PointDto(point.userId, point.balance)
    }
}
