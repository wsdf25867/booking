package io.concert.booking.application.point

import io.concert.booking.application.point.dto.PointChargeDto
import io.concert.booking.application.point.dto.PointDto
import io.concert.booking.domain.point.Point
import io.concert.booking.domain.point.PointHistoryRepository
import io.concert.booking.domain.point.PointRepository
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
