package io.concert.booking.application.concert

import io.concert.booking.application.concert.dto.ConcertSearchDto
import io.concert.booking.application.concert.dto.ConcertDto
import io.concert.booking.domain.concert.ConcertRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ConcertService(
    private val concertRepository: ConcertRepository
) {

    fun findAllBookable(cond: ConcertSearchDto): List<ConcertDto> =
        concertRepository.findAllByDateGreaterThan(cond.date)
            .map(ConcertDto::from)
}