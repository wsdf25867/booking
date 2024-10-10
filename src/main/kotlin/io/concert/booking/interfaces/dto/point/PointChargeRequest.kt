package io.concert.booking.interfaces.dto.point

data class PointChargeRequest(
    val userId: Long,
    val amount: Int,
) {

}
