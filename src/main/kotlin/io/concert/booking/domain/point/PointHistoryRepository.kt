package io.concert.booking.domain.point

interface PointHistoryRepository {

    fun save(pointHistory: PointHistory): PointHistory
    fun findAllByPointId(pointId: Long): List<PointHistory>
}
