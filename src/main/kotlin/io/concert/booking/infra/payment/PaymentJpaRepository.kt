package io.concert.booking.infra.payment

import io.concert.booking.domain.payment.Payment
import io.concert.booking.domain.payment.PaymentRepository
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentJpaRepository: JpaRepository<Payment, Long>, PaymentRepository {
}
