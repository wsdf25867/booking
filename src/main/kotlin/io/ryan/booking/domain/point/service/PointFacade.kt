package io.ryan.booking.domain.point.service

import io.ryan.booking.domain.point.dto.PointChargeDto
import io.ryan.booking.domain.point.dto.PointDto
import io.ryan.booking.domain.point.domain.Point
import io.ryan.booking.domain.point.domain.PointHistoryRepository
import io.ryan.booking.domain.point.domain.PointRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PointFacade(
    private val pointRepository: PointRepository,
    private val pointHistoryRepository: PointHistoryRepository,
) {

    @Transactional(readOnly = true)
    fun find(userId: Long): PointDto {
        val point = requireNotNull(pointRepository.findByUserId(userId)) {
            "포인트 정보가 존재하지 않습니다."
        }
        return PointDto.from(point)
    }

    @Transactional
    fun charge(dto: PointChargeDto): PointDto {
        val point = requireNotNull(pointRepository.findByUserIdWithLock(dto.userId)) {
            "포인트 정보가 존재하지 않습니다. userId = ${dto.userId}"
        }
        val history = point.charge(dto.amount)
        pointHistoryRepository.save(history)
        return PointDto.from(point)
    }

    @PostConstruct
    fun init() {
        for (i in 1..50) {
            pointRepository.save(Point(userId = i.toLong()))
        }
    }
}
