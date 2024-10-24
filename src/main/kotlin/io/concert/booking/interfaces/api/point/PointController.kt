package io.concert.booking.interfaces.api.point

import io.concert.booking.application.point.PointService
import io.concert.booking.interfaces.dto.point.PointChargeRequest
import io.concert.booking.interfaces.dto.point.PointResponse
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/points")
class PointController(
    private val pointService: PointService
): PointApiSpecification {

    @PatchMapping
    override fun charge(@RequestBody point: PointChargeRequest): PointResponse {
        return PointResponse(BigDecimal(100))
    }

    @GetMapping("/users/{userId}")
    override fun getPoint(@PathVariable("userId") userId: Long):  PointResponse {
        return PointResponse(BigDecimal(100))
    }
}
