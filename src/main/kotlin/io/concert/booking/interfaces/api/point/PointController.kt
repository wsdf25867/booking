package io.concert.booking.interfaces.api.point

import io.concert.booking.application.point.PointFacade
import io.concert.booking.application.point.dto.PointChargeDto
import io.concert.booking.interfaces.dto.point.PointChargeRequest
import io.concert.booking.interfaces.dto.point.PointResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/points")
class PointController(
    private val pointFacade: PointFacade
): PointApiSpecification {

    @PatchMapping
    override fun charge(@RequestBody point: PointChargeRequest): PointResponse {
        val pointDto = pointFacade.charge(PointChargeDto(point.userId, point.amount))
        return PointResponse(pointDto.balance)
    }

    @GetMapping("/users/{userId}")
    override fun getPoint(@PathVariable("userId") userId: Long):  PointResponse {
        val pointDto = pointFacade.find(userId)
        return PointResponse(pointDto.balance)
    }
}
