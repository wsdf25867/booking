package io.concert.booking.infra.point

import io.concert.booking.domain.point.PointHistory
import io.concert.booking.domain.point.PointHistoryRepository
import org.springframework.data.jpa.repository.JpaRepository

interface PointHistoryJpaRepository: JpaRepository<PointHistory, Long>, PointHistoryRepository {
}
