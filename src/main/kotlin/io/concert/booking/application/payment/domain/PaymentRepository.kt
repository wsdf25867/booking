package io.concert.booking.application.payment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository: JpaRepository<Payment, Long> {

}
