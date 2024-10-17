package io.concert.booking.domain.point

interface PointHistoryRepository {

    fun save(pointHistory: PointHistory): PointHistory
}
