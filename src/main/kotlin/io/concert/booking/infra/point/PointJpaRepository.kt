package io.concert.booking.infra.point

import io.concert.booking.domain.point.Point
import io.concert.booking.domain.point.PointRepository
import org.springframework.data.jpa.repository.JpaRepository

interface PointJpaRepository: JpaRepository<Point, Long>, PointRepository {
}
