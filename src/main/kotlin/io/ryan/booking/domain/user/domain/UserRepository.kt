package io.ryan.booking.domain.user.domain

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}
