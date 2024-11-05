package io.concert.booking.domain.concert

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ConcertService(
    private val concertRepository: ConcertRepository
) {
    fun get(concertId: Long): Concert {
        val concert = concertRepository.findById(concertId)
        return requireNotNull(concert) {
            "콘서트 정보가 존재하지 않습니다"
        }
    }

    fun getBookable(date: LocalDateTime): List<Concert> =
        concertRepository.findAllByDateGreaterThan(date)
    
}
