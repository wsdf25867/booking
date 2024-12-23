package io.ryan.booking.domain.booking.application

data class BookingCreateRequest(
    val userId: Long,
    val concertScheduleId: Long,
    val seatIds: List<Long>
) {
}
