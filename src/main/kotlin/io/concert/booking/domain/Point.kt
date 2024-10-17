package io.concert.booking.domain

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
    fun charge(amount: BigDecimal) {
        require(amount > BigDecimal.ZERO) { "양수만 가능" }

        balance += amount
    }

    fun use(amount: BigDecimal) {
        require(amount > BigDecimal.ZERO) { "양수만 가능" }
        check(amount <= balance) { "잔액 부족" }

        balance -= amount
    }
}