package io.concert.booking.application.concert

import com.fasterxml.jackson.core.type.TypeReference
import io.concert.booking.application.cache.CacheService
import io.concert.booking.application.concert.dto.ConcertResult
import io.concert.booking.application.concert.dto.ConcertWithSeatsResult
import io.concert.booking.domain.concert.ConcertService
import io.concert.booking.domain.seat.SeatService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Service
class ConcertFacade(
    private val concertService: ConcertService,
    private val seatService: SeatService,
    private val cacheService: CacheService,
) {

    @Transactional(readOnly = true)
    fun getBookable(date: LocalDateTime): List<ConcertResult> {
        return cacheService.get(date, object : TypeReference<List<ConcertResult>>() {})
            ?: concertService.getBookable(date)
                .map(ConcertResult::from)
                .let {
                    cacheService.set(date, it, 1, TimeUnit.DAYS, object : TypeReference<List<ConcertResult>>() {})
                }
    }

    @Transactional(readOnly = true)
    fun getBookableWithSeats(concertId: Long): ConcertWithSeatsResult {
        return cacheService.get(concertId, ConcertWithSeatsResult::class.java)
            ?: ConcertWithSeatsResult.of(
                concertService.get(concertId),
                seatService.getBookable(concertId)
            ).let {
                cacheService.set(
                    concertId,
                    it,
                    5,
                    TimeUnit.DAYS,
                    object : TypeReference<ConcertWithSeatsResult>() {})
            }


    }
}
