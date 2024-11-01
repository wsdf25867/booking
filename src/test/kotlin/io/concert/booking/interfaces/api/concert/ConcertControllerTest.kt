package io.concert.booking.interfaces.api.concert

import io.concert.booking.application.booking.BookingApplicationService
import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.application.booking.dto.BookingDto
import io.concert.booking.application.concert.ConcertApplicationService
import io.concert.booking.application.concert.dto.ConcertDto
import io.concert.booking.application.concert.dto.ConcertSearchDto
import io.concert.booking.application.seat.SeatApplicationService
import io.concert.booking.application.seat.dto.SeatBookableDto
import io.concert.booking.application.seat.dto.SeatDto
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.time.LocalDate
import java.util.*

@WebMvcTest(ConcertController::class)
class ConcertControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var concertApplicationService: ConcertApplicationService

    @MockBean
    lateinit var seatApplicationService: SeatApplicationService

    @MockBean
    lateinit var bookingApplicationService: BookingApplicationService

    @Test
    fun `예약 가능한 콘서트 목록을 보여줍니다`() {
        // given
        val date = LocalDate.of(1995, 3, 26)
        given(concertApplicationService.findAllBookable(ConcertSearchDto(date.atStartOfDay())))
            .willReturn(listOf(ConcertDto("some-name", date.atStartOfDay(), 1)))
        // when // then
        mockMvc.get("/api/v1/concerts") {
            queryParam("date", date.toString())
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect {
                jsonPath("\$[*]") { isArray() }
                jsonPath("\$[0].id") { value(1) }
                jsonPath("\$[0].name") { value("some-name") }
                jsonPath("\$[0].date") { isString() }
            }
    }

    @Test
    fun `예약 가능한 콘서트 좌석을 보여줍니다`() {
        // given
        val date = LocalDate.of(1995, 3, 26)
        given(concertApplicationService.findAllBookable(ConcertSearchDto(date.atStartOfDay())))
            .willReturn(listOf(ConcertDto("some-name", date.atStartOfDay(), 123)))
        val token = UUID.randomUUID()
        given(seatApplicationService.findBookable(SeatBookableDto(concertId = 123, token)))
            .willReturn(listOf(SeatDto(1, 100.toBigDecimal(), 1)))
        // when // then
        mockMvc.get("/api/v1/concerts/123/seats") {
            queryParam("date", date.toString())
            queryParam("token", token.toString())
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect {
                jsonPath("\$.concertId") { value(123) }
                jsonPath("\$.name") { value("some-name") }
                jsonPath("\$.date") { isString() }
                jsonPath("\$.seats") { isArray() }
            }
    }

    @Test
    fun `콘서트 좌석을 예약 요청`() {
        // given
        given(bookingApplicationService.create(BookingCreateDto(1, "token")))
            .willReturn(BookingDto(1, 1, 1))

        // when // then
        mockMvc.post("/api/v1/bookings") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "token": "token",
                    "concertId": 1,
                    "seatId": 1
                }
            """.trimIndent()
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect {
                jsonPath("\$.bookingId") { value(1) }
                jsonPath("\$.concertId") { value(1) }
                jsonPath("\$.seatId") { value(1) }
                jsonPath("\$.bookedAt") { isString() }
            }
    }
}
