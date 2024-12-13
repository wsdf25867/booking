package io.ryan.booking.domain.booking.application

import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.booking.dto.BookingCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
) {

    @Transactional
    fun create(request: BookingCreateRequest): Long {


        TODO()
    }
}
