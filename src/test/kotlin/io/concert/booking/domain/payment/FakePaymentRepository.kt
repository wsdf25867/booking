package io.concert.booking.domain.payment


class FakePaymentRepository: PaymentRepository {

    private val store: MutableMap<Long, Payment> = mutableMapOf()
    private var sequence: Long = 1
    override fun save(payment: Payment): Payment =
        Payment(
            payment.bookingId,
            payment.amount,
            sequence++,
        ).also {
            store[it.id] = it
        }
}
