package io.concert.booking.domain.concert

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

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

    @Test
    fun `조회 일자보다 나중에 시작하는 콘서트 조회가능`() {
        // given
        val date = LocalDateTime.of(1995, 3, 26, 0, 0)
        sut.save(Concert(name = "some concert", date = date))
        sut.save(Concert(name = "some concert", date = date))

        // when
        val found = sut.findAllByDateGreaterThan(LocalDateTime.of(1994, 3, 26, 0, 0))

        // then
        assertThat(found).hasSize(2)
    }
}

