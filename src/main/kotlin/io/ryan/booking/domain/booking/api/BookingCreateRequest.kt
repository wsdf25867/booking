package io.ryan.booking.domain.booking.api

import io.ryan.booking.domain.booking.application.BookingCreateServiceRequest

data class BookingCreateRequest(
    val userId: Long,
    val concertScheduleId: Long,
    val seatIds: List<Long>,
) {
    fun toServiceRequest(): BookingCreateServiceRequest {
        return BookingCreateServiceRequest(userId, concertScheduleId, seatIds)
    }
}
