package io.concert.booking.domain.point

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "points")
class Point(
    val userId: Long,
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
