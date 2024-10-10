package io.concert.booking.interfaces.api.point

import io.concert.booking.interfaces.dto.point.PointChargeRequest
import io.concert.booking.interfaces.dto.point.PointResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/points")
class PointController {

    @PostMapping
    fun charge(@RequestBody point: PointChargeRequest): PointResponse {
        return PointResponse(100)
    }

    @GetMapping("/users/{userId}")
    fun getPoint(@PathVariable("userId") userId: Long):  PointResponse {
        return PointResponse(100)
    }
}