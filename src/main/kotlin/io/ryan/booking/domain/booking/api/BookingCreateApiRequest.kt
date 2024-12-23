package io.ryan.booking.domain.booking.api

import io.ryan.booking.domain.booking.application.BookingCreateRequest

data class BookingCreateApiRequest(
    val userId: Long,
    val concertScheduleId: Long,
    val seatIds: List<Long>,
) {
    fun toServiceRequest(): BookingCreateRequest {
        return BookingCreateRequest(userId, concertScheduleId, seatIds)
    }
}
