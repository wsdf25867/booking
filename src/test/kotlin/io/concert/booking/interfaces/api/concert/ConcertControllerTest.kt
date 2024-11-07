package io.concert.booking.interfaces.api.concert

import io.concert.booking.application.booking.BookingFacade
import io.concert.booking.application.booking.dto.BookingCreateDto
import io.concert.booking.application.booking.dto.BookingResult
import io.concert.booking.application.concert.ConcertFacade
import io.concert.booking.application.concert.dto.ConcertResult
import io.concert.booking.application.concert.dto.ConcertWithSeatsResult
import io.concert.booking.application.seat.dto.SeatResult
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

@WebMvcTest(ConcertController::class)
class ConcertControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var concertFacade: ConcertFacade

    @MockBean
    lateinit var bookingFacade: BookingFacade

    @Test
    fun `예약 가능한 콘서트 목록을 보여줍니다`() {
        // given
        val date = LocalDate.of(1995, 3, 26)
        given(concertFacade.getBookable(date.atStartOfDay()))
            .willReturn(listOf(ConcertResult("some-name", date.atStartOfDay(), 1)))
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
        given(concertFacade.getBookableWithSeats(1L))
            .willReturn(ConcertWithSeatsResult(ConcertResult("some-name", date.atStartOfDay(), 1L), listOf(SeatResult(1L, 100.toBigDecimal(), 1))))
        // when // then
        mockMvc.get("/api/v1/concerts/123/seats") {
            queryParam("date", date.toString())
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
        given(bookingFacade.bookSeat(BookingCreateDto(1, "token")))
            .willReturn(BookingResult(1, 1, 1))

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
