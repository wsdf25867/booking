package io.concert.booking.domain.concert

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConcertRepositoryTest {

    private val sut: ConcertRepository = FakeConcertRepository()

    @Test
    fun `콘서트를 저장할 수 있다`() {
        // given
        val concert = Concert("some concert")
        // when
        val saved = sut.save(concert)

        // then
        assertThat(saved.id).isNotEqualTo(0)
        assertThat(saved.name).isEqualTo("some concert")
    }

    @Test
    fun `id로 콘서트를 조회할 수 있다`() {
        // given
        val concert = Concert("some concert")
        sut.save(concert)

        // when
        val found = sut.findById(1)

        // then
        assertThat(found).isNotNull
    }
}

