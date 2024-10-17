package io.concert.booking.domain.point

class FakePointHistoryRepository: PointHistoryRepository {

    private val store: MutableMap<Long, PointHistory> = mutableMapOf()
    private var sequence: Long = 1

    override fun save(pointHistory: PointHistory): PointHistory =
        PointHistory(
            pointHistory.pointId,
            pointHistory.amount,
            pointHistory.operationType,
            pointHistory.createdAt,
            sequence++
        ).also {
            store[it.id] = it
        }

}
