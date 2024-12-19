package io.ryan.booking.domain.point.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "point_history")
class PointHistory(
    val pointId: Long,
    val amount: BigDecimal,
    val operationType: PointOperationType,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue
    val id: Long = 0,
) {
}
