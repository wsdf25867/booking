package io.concert.booking.interfaces.api.payment

import com.fasterxml.jackson.databind.ObjectMapper
import io.concert.booking.interfaces.dto.payment.PaymentRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(PaymentController::class)
class PaymentControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun `결제요청을 성공하면 결제 정보를 반환한다`() {
        // given
        val request = PaymentRequest(1, 1, 1, 1)
        // when // then
        mockMvc.post("/api/v1/payments") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(request)
        }.andDo { print() }
            .andExpect {
                status { is2xxSuccessful() }
                jsonPath("\$.paymentId") { value(1) }
                jsonPath("\$.paidAmount") { value(100) }
                jsonPath("\$.balance") { value(0) }
            }
    }
}
