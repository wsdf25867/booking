package io.ryan.booking.domain.concert.service

import com.fasterxml.jackson.core.type.TypeReference
import io.ryan.booking.domain.cache.service.CacheService
import io.ryan.booking.domain.concert.dto.ConcertResult
import io.ryan.booking.domain.concert.dto.ConcertWithSeatsResult
import io.ryan.booking.domain.seat.service.SeatQueryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Service
class ConcertFacade(
    private val concertService: ConcertQueryService,
    private val seatService: SeatQueryService,
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
                seatService.getAllEmpty(concertId)
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
