package io.ryan.booking.domain.point.api

import io.ryan.booking.domain.point.application.PointService
import io.ryan.booking.domain.point.dto.PointChargeRequest
import io.ryan.booking.domain.point.dto.PointResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/points")
class PointController(
    private val pointService: PointService
): PointApiSpecification {

    @PatchMapping
    override fun charge(@Valid @RequestBody request: PointChargeRequest): PointResponse {
        return pointService.charge(request.toServiceRequest())
    }

    @GetMapping
    override fun getPoint(@RequestParam(required = true) userId: Long): PointResponse {
        return pointService.find(userId)
    }
}
