package io.concert.booking.interfaces.api.point

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.interfaces.dto.point.PointChargeRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(PointController::class)
class PointControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun `유저가 포인트 충전요청에 성공하면 잔액을 반환한다`() {
        // given
        val request = PointChargeRequest(1, 100)
        // when // then
        mockMvc.post("/api/v1/points") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(request)
        }
            .andDo { print() }
            .andExpect { status().isOk }
            .andExpect {
                jsonPath("\$.balance") { value(100) }
            }
    }

    @Test
    fun `유저가 포인트 잔액을 조회`() {
        // given
        // when // then
        mockMvc.get("/api/v1/points/users/1") {
        }
            .andDo { print() }
            .andExpect { status().isOk }
            .andExpect {
                jsonPath("\$.balance") { value(100) }
            }
    }
}
