package io.concert.booking.interfaces.api

import io.concert.booking.interfaces.api.queue.TokenController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(TokenController::class)
class TokenControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `유저에게 토큰을 발급요청을 하면 userId 토큰 만료시간을 반환한다`() {
        // given
        val requestBody = """
            {"userId": 1}
        """.trimIndent()
        // when // then
        mockMvc.post("/api/v1/tokens/") {
            contentType = MediaType.APPLICATION_JSON
            content = requestBody
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    jsonPath("\$.userId") { value(1) }
                    jsonPath("\$.token") { isString() }
                    jsonPath("\$.expiredAt") { isString() }
                }
            }
    }

    @Test
    fun `토큰 조회를 통해 현재 대기열 정보를 알 수 있다`() {
        // given
        val token = "token"

        // when // then
        mockMvc.get("/api/v1/tokens/$token")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    jsonPath("\$.queuePosition") { value(12) }
                    jsonPath("\$.queueSize") { value(20) }
                }
            }
    }
}
