package io.ryan.booking.domain.point.api

import io.ryan.booking.domain.point.dto.PointChargeRequest
import io.ryan.booking.domain.point.dto.PointResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable

@Tag(name = "Point")
interface PointApiSpecification {

    @PatchMapping
    fun charge(request: PointChargeRequest): PointResponse
    @GetMapping("/users/{userId}")
    fun getPoint(@PathVariable userId: Long): PointResponse
}
