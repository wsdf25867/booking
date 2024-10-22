package io.concert.booking.domain.point

class FakePointRepository : PointRepository {

    private val store: MutableMap<Long, Point> = mutableMapOf()
    private var sequence: Long = 1

    override fun findByUserId(userId: Long): Point? {
        return store.values.find { it.userId == userId }
    }

    override fun findByUserIdWithLock(userId: Long): Point? {
        return findByUserId(userId)
    }

    override fun save(point: Point): Point {
        return Point(point.userId, point.balance, sequence++).also {
            store[it.id] = it
        }
    }
}
