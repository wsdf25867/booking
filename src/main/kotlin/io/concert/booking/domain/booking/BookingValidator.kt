package io.concert.booking.domain.booking

import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.queue.Token
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class BookingValidator {

    fun validate(token: Token, concert: Concert) {
        check(token.isPass()) { "아직 대기중인 토큰 입니다" }
        check(concert.isBookableAt(LocalDateTime.now())) { "예약할 수 없는 콘서트입니다." }
    }
}
