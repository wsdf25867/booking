package io.ryan.booking.domain.booking.api

import io.ryan.booking.domain.booking.application.BookingCreateCommand

data class BookingCreateRequest(
    val userId: Long,
    val concertScheduleId: Long,
    val seatIds: List<Long>,
) {
    fun toCommand(): BookingCreateCommand {
        return BookingCreateCommand(userId, concertScheduleId, seatIds)
    }
}
