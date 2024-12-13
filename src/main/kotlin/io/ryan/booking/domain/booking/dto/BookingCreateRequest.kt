package io.ryan.booking.domain.booking.dto

data class BookingCreateRequest(
    val userId: Long,
    val concertScheduleId: Long,
    val seatIds: List<Long>,
)
