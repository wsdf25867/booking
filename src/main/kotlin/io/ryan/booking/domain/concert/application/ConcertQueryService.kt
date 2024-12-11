package io.ryan.booking.domain.concert.application

import io.ryan.booking.domain.concert.domain.Concert
import io.ryan.booking.domain.concert.domain.ConcertRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class ConcertQueryService(
    private val concertRepository: ConcertRepository
) {
    fun get(concertId: Long): Concert {
        val concert = concertRepository.findByIdOrNull(concertId)
        return requireNotNull(concert) {
            "콘서트 정보가 존재하지 않습니다"
        }
    }

    fun getBookable(date: LocalDateTime): List<Concert> =
        concertRepository.findAllByDateGreaterThan(date)
    
}
