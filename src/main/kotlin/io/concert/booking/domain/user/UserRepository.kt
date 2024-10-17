package io.concert.booking.domain.user

interface UserRepository {
    fun findById(id: Long): User?

    fun save(user: User): User
}
