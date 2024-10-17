package io.concert.booking.domain.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserRepositoryTest {

    private val sut: UserRepository = FakeUserRepository()

    @Test
    fun `유저를 저장할 수 있다`() {
        // given
        val user = User("강지우")

        // when
        val saved = sut.save(user)

        // then
        assertThat(saved.id).isNotEqualTo(0)
        assertThat(saved.name).isEqualTo("강지우")
    }

    @Test
    fun `id로 유저를 찾을 수 있다`() {
        // given
        val user = User("강지우")
        sut.save(user)

        // when
        val found = sut.findById(1L)

        // then
        assertThat(found).isNotNull
    }
}


