package io.ryan.booking.domain.concert.api

import io.ryan.booking.domain.concert.application.ConcertQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api/v1")
class ConcertController(
    private val concertQueryService: ConcertQueryService,
) : ConcertApiSpecification {

}
