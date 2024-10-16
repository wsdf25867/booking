package io.concert.booking.infra.user

import io.concert.booking.domain.user.User
import io.concert.booking.domain.user.UserRepository
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, Long>, UserRepository {
}
