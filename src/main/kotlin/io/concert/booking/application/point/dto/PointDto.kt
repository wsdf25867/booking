package io.concert.booking.application.point.dto

import io.concert.booking.application.point.domain.Point
import java.math.BigDecimal

data class PointDto(
    val userId: Long,
    val balance: BigDecimal,
) {
    companion object {
        fun from(point: Point) = PointDto(point.userId, point.balance)
    }
}
