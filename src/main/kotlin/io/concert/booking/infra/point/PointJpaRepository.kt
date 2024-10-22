package io.concert.booking.infra.point

import io.concert.booking.domain.point.Point
import io.concert.booking.domain.point.PointRepository
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface PointJpaRepository: JpaRepository<Point, Long>, PointRepository {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Point p where p.userId = :userId")
    override fun findByUserIdWithLock(userId: Long): Point?
}
