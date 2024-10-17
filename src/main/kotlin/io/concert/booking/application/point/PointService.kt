package io.concert.booking.application.point

import io.concert.booking.application.point.dto.PointChargeDto
import io.concert.booking.application.point.dto.PointDto
import io.concert.booking.domain.point.PointRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PointService(
    private val pointRepository: PointRepository
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
            "포인트 정보가 존재하지 않습니다."
        }
        point.charge(dto.amount)
        return PointDto.from(point)
    }
}
