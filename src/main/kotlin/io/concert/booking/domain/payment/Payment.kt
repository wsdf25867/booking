package io.concert.booking.domain.payment

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "payments")
class Payment(
    val bookingId: Long,
    val amount: BigDecimal,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {
}
