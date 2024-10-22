package io.concert.booking.domain.payment

interface PaymentRepository {

    fun save(payment: Payment): Payment
}
