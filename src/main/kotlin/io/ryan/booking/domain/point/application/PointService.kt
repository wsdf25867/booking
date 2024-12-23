package io.ryan.booking.domain.point.application

import io.ryan.booking.domain.point.dto.PointChargeCommand
import io.ryan.booking.domain.point.domain.PointHistoryRepository
import io.ryan.booking.domain.point.domain.PointRepository
import io.ryan.booking.domain.point.dto.PointResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PointService(
    private val pointRepository: PointRepository,
    private val pointHistoryRepository: PointHistoryRepository,
) {

    @Transactional(readOnly = true)
    fun find(userId: Long): PointResponse {
        val point =
            pointRepository.findByUserId(userId) ?:
            throw EntityNotFoundException("포인트 정보가 존재하지 않습니다. userId = $userId")

        return PointResponse.from(point)
    }

    @Transactional
    fun charge(request: PointChargeCommand): PointResponse {
        val point = pointRepository.findByUserIdWithLock(request.userId)
            ?: throw EntityNotFoundException("포인트 정보가 존재하지 않습니다. userId = ${request.userId}")

        val history = point.charge(request.amount)
        pointHistoryRepository.save(history)
        return PointResponse.from(point)
    }
}
