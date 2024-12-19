package io.ryan.booking.domain.point.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "points")
class Point(
    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    var balance: BigDecimal = BigDecimal.ZERO,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {
    fun charge(amount: BigDecimal): PointHistory {
        require(amount > BigDecimal.ZERO) { "양수만 가능" }

        balance += amount

        return PointHistory(
            id,
            amount,
            PointOperationType.CHARGE,
        )
    }

    fun use(amount: BigDecimal): PointHistory {
        require(amount > BigDecimal.ZERO) { "양수만 가능" }
        check(amount <= balance) { "잔액 부족" }

        balance -= amount

        return PointHistory(
            id,
            amount,
            PointOperationType.USE,
        )
    }
}
