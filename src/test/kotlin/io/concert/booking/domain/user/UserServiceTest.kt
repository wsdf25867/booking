package io.concert.booking.domain.user

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class UserServiceTest {

    private val sut: UserService = UserService(FakeUserRepository())

    @Test
    fun `유저 정보가 없을 경우 IllegalArgumentException 을 던진다`() {
        // given
        // when
        // then
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            sut.get(1L)
        }
    }
}
