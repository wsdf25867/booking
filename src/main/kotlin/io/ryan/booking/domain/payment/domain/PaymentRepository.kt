package io.ryan.booking.domain.payment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository: JpaRepository<Payment, Long> {

}
